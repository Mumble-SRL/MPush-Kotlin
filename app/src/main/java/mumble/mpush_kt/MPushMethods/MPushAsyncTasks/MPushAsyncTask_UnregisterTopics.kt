package mumble.mpush_kt.MPushMethods.MPushAsyncTasks

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import mumble.mburger.sdk.kt.Common.MBApiManager.MBAMActivityUtils.Companion.sendBroadcastMessage
import mumble.mburger.sdk.kt.Common.MBApiManager.MBAPIManager4.Companion.callApi
import mumble.mburger.sdk.kt.Common.MBApiManager.MBApiManagerConfig.Companion.MODE_POST
import mumble.mburger.sdk.kt.Common.MBApiManager.MBApiManagerUtils.Companion.hasMapOkResults
import mumble.mpush_kt.Common.MPCommonMethods.getErrorMessageFromResult
import mumble.mpush_kt.Common.MPConstants.MPAPIConstants
import mumble.mpush_kt.Common.MPConstants.MPApiManagerConfig
import mumble.mpush_kt.Common.MPConstants.MPushUserConstants.pushKey
import mumble.mpush_kt.MPushMethods.MPushResultsListener.MPushUnregisterTopicsListener
import org.json.JSONArray
import java.lang.ref.WeakReference

class MPushAsyncTask_UnregisterTopics : AsyncTask<Void, Void, Void> {
    /**
     * Context reference used to send data to Activity/Fragment
     */
    private var weakContext: WeakReference<Context>

    /**
     * Android device id
     */
    private var device_id: String

    /**
     * Topics to unregister
     */
    private var topics: JSONArray

    /**
     * If you wish to change the action that accompanies the API result
     */
    private var action = MPAPIConstants.ACTION_UNREGISTER_TOPICS

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private var listener: MPushUnregisterTopicsListener? = null
    private var result = MPApiManagerConfig.COMMON_INTERNAL_ERROR
    private var error: String? = null
    private var map: MutableMap<String, Any?>? = null

    constructor(context: Context, device_id: String, topics: JSONArray) {
        weakContext = WeakReference(context)
        this.device_id = device_id
        this.topics = topics
    }

    constructor(context: Context, custom_action: String, device_id: String, topics: JSONArray) {
        weakContext = WeakReference(context)
        action = custom_action
        this.device_id = device_id
        this.topics = topics
    }

    constructor(
        context: Context,
        listener: MPushUnregisterTopicsListener?,
        device_id: String,
        topics: JSONArray
    ) {
        weakContext = WeakReference(context)
        this.listener = listener
        this.device_id = device_id
        this.topics = topics
    }

    protected override fun doInBackground(vararg arg0: Void): Void? {
        putValuesAndCall()
        if (hasMapOkResults(map, false)) {
            result = MPApiManagerConfig.RESULT_OK
        } else {
            result = if (map!!.containsKey(MPApiManagerConfig.AM_RESULT)) {
                map!![MPApiManagerConfig.AM_RESULT] as Int
            } else {
                MPApiManagerConfig.COMMON_INTERNAL_ERROR
            }
            error = if (map!!.containsKey(MPApiManagerConfig.AM_ERROR)) {
                map!![MPApiManagerConfig.AM_ERROR] as String?
            } else {
                getErrorMessageFromResult(weakContext.get()!!, result)
            }
        }
        return null
    }

    override fun onPostExecute(aVoid: Void?) {
        if (weakContext != null) {
            if (listener == null) {
                val i = Intent(action)
                i.putExtra("result", result)
                i.putExtra("error", error)
                sendBroadcastMessage(
                    weakContext.get()!!, i
                )
            } else {
                if (error != null) {
                    listener!!.onTopicsUnregisteredError(error)
                } else {
                    listener!!.onTopicsUnregistered()
                }
            }
        }
    }

    fun putValuesAndCall() {
        val valuesHeaders = ContentValues()
        valuesHeaders.put("X-MPush-Token", pushKey)
        val values = ContentValues()
        values.put("topics", topics.toString())
        values.put("device_id", device_id)
        map = callApi(
            weakContext.get()!!,
            MPApiManagerConfig.API_UNREGISTER_TOPICS, values, MODE_POST,
            false, false, MPApiManagerConfig.endpoint_push, MPApiManagerConfig.SERVER_HOSTNAME_PUSH,
            true, valuesHeaders, null
        )
    }
}