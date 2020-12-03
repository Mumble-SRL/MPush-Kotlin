package mumble.mpush_kt

import android.content.Context
import mumble.mburger.sdk.kt.Common.MBCommonMethods
import mumble.mpush_kt.Common.MPConstants.MPushUserConstants
import mumble.mpush_kt.MPushData.MPTopic

object MPush {

    fun init(token: String) {
        MPushUserConstants.pushKey = token
    }

    fun projectPushTopic(context: Context?): MPTopic {
        return MPTopic("project.all", "All users", false)
    }

    fun devicePushTopic(context: Context): MPTopic {
        val device_id: String = MBCommonMethods.getDeviceId(context)
        return MPTopic(device_id, "Device: $device_id", true)
    }
}