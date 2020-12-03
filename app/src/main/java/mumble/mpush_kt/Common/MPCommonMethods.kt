package mumble.mpush_kt.Common

import android.content.Context
import mumble.mbpush_kt.R
import mumble.mpush_kt.Common.MPConstants.MPApiManagerConfig
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * A list of static methods used througout the SDK
 *
 * @author Enrico Ori
 * @version {@value MPushConstants#version}
 */
object MPCommonMethods {

    fun getErrorMessageFromResult(context: Context, result: Int): String {
        return when (result) {
            MPApiManagerConfig.COMMON_INTERNAL_ERROR -> context.getString(R.string.internal_error)
            MPApiManagerConfig.COMMON_IOERROR -> context.getString(R.string.internal_error)
            MPApiManagerConfig.COMMON_NOINTERNET -> context.getString(R.string.no_internet_error)
            MPApiManagerConfig.COMMON_TIMEOUT -> context.getString(R.string.timeout_operation_error)
            else -> context.getString(R.string.internal_error)
        }
    }

    fun md5(s: String): String {
        try {
            // Create MD5 Hash
            val digest = MessageDigest.getInstance("MD5")
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuffer()
            for (i in messageDigest.indices) hexString.append(
                Integer.toHexString(
                    0xFF and messageDigest[i]
                        .toInt()
                )
            )
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}