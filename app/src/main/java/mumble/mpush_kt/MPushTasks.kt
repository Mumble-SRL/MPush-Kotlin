package mumble.mpush_kt

import android.content.Context
import mumble.mbpush_kt.R
import mumble.mpush_kt.Common.MPConstants.MPushUserConstants.pushKey
import mumble.mpush_kt.Common.MPExceptions.MPSDKInitializeException
import mumble.mpush_kt.MPushData.MPTopic
import mumble.mpush_kt.MPushMethods.MPushAsyncTasks.MPushAsyncTask_RegisterTopics
import mumble.mpush_kt.MPushMethods.MPushAsyncTasks.MPushAsyncTask_SendToken
import mumble.mpush_kt.MPushMethods.MPushAsyncTasks.MPushAsyncTask_UnregisterAllTopics
import mumble.mpush_kt.MPushMethods.MPushAsyncTasks.MPushAsyncTask_UnregisterTopics
import mumble.mpush_kt.MPushMethods.MPushResultsListener.MPushRegisterTopicsListener
import mumble.mpush_kt.MPushMethods.MPushResultsListener.MPushSendTokenListener
import mumble.mpush_kt.MPushMethods.MPushResultsListener.MPushUnregisterAllTopicsListener
import mumble.mpush_kt.MPushMethods.MPushResultsListener.MPushUnregisterTopicsListener
import org.json.JSONArray
import java.util.*

object MPushTasks {
    /**
     * Register device to receive push notifications
     */
    fun sendToken(context: Context, device_id: String, token: String) {
        if (pushKey != null) {
            MPushAsyncTask_SendToken(context, device_id, token).execute()
        } else {
            throw MPSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized))
        }
    }

    /**
     * Register device to receive push notifications with custom action callback
     */
    fun sendToken(context: Context, custom_action: String, device_id: String, token: String) {
        if (pushKey != null) {
            MPushAsyncTask_SendToken(context, custom_action, device_id, token).execute()
        } else {
            throw MPSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized))
        }
    }

    /**
     * Register device to receive push notifications with listener callback
     */
    fun sendToken(
        context: Context,
        listener: MPushSendTokenListener,
        device_id: String,
        token: String
    ) {
        if (pushKey != null) {
            MPushAsyncTask_SendToken(context, listener, device_id, token).execute()
        } else {
            throw MPSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized))
        }
    }

    /**
     * Register push channels
     */
    fun registerTopics(context: Context, device_id: String, topics: ArrayList<MPTopic>) {
        if (pushKey != null) {
            MPushAsyncTask_RegisterTopics(context, device_id, topics).execute()
        } else {
            throw MPSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized))
        }
    }

    /**
     * Register push channels with custom action callback
     */
    fun registerTopics(
        context: Context,
        custom_action: String,
        device_id: String,
        topics: ArrayList<MPTopic>
    ) {
        if (pushKey != null) {
            MPushAsyncTask_RegisterTopics(context, custom_action, device_id, topics).execute()
        } else {
            throw MPSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized))
        }
    }

    /**
     * Register push channels with listener callback
     */
    fun registerTopics(
        context: Context,
        listener: MPushRegisterTopicsListener,
        device_id: String,
        topics: ArrayList<MPTopic>
    ) {
        if (pushKey != null) {
            MPushAsyncTask_RegisterTopics(context, listener, device_id, topics).execute()
        } else {
            throw MPSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized))
        }
    }

    /**
     * Unregister push channels
     */
    fun unregisterTopics(context: Context, device_id: String, topics: JSONArray) {
        if (pushKey != null) {
            MPushAsyncTask_UnregisterTopics(context, device_id, topics).execute()
        } else {
            throw MPSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized))
        }
    }

    /**
     * Unregister push channels with custom action callback
     */
    fun unregisterTopics(
        context: Context,
        custom_action: String,
        device_id: String,
        topics: JSONArray
    ) {
        if (pushKey != null) {
            MPushAsyncTask_UnregisterTopics(
                context,
                custom_action,
                device_id,
                topics
            ).execute()
        } else {
            throw MPSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized))
        }
    }

    /**
     * Unregister push channels with listener callback
     */
    fun unregisterTopics(
        context: Context,
        listener: MPushUnregisterTopicsListener,
        device_id: String,
        topics: JSONArray
    ) {
        if (pushKey != null) {
            MPushAsyncTask_UnregisterTopics(context, listener, device_id, topics).execute()
        } else {
            throw MPSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized))
        }
    }

    /**
     * Unregister all push channels
     */
    fun unregisterAllTopics(context: Context, device_id: String) {
        if (pushKey != null) {
            MPushAsyncTask_UnregisterAllTopics(context, device_id).execute()
        } else {
            throw MPSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized))
        }
    }

    /**
     * Unregister all push channels with custom action callback
     */
    fun unregisterAllTopics(context: Context, custom_action: String, device_id: String) {
        if (pushKey != null) {
            MPushAsyncTask_UnregisterAllTopics(context, custom_action, device_id).execute()
        } else {
            throw MPSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized))
        }
    }

    /**
     * Unregister all push channels with listener callback
     */
    fun unregisterAllTopics(
        context: Context,
        listener: MPushUnregisterAllTopicsListener,
        device_id: String
    ) {
        if (pushKey != null) {
            MPushAsyncTask_UnregisterAllTopics(context, listener, device_id).execute()
        } else {
            throw MPSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized))
        }
    }
}