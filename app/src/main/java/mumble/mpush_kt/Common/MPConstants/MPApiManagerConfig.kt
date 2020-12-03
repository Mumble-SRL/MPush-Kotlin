package mumble.mpush_kt.Common.MPConstants

object MPApiManagerConfig {

    const val endpoint_push = "https://app.mpush.cloud"
    const val SERVER_HOSTNAME_PUSH = "app.mpush.cloud"
    const val MODE_POST = -1
    const val MODE_GET = -2
    const val MODE_DELETE = -3
    const val MODE_PUT = -4

    /**
     * API
     */
    const val API = "/api"

    /**
     * PUSH API
     */
    const val API_TOKENS_PUSH = "/api/tokens"
    const val API_REGISTER_TOPICS = "/api/register"
    const val API_UNREGISTER_TOPICS = "/api/unregister"
    const val API_UNREGISTER_ALL_TOPICS = "/api/unregister-all"

    /**
     * ERRORS
     */
    const val STATUS_CODE_OK = 0
    const val STATUS_CODE_LOGOUT = 55
    const val RESULT_OK = 200
    const val RESULT_REFRESH = 401
    const val COMMON_IOERROR = -1000
    const val COMMON_TIMEOUT = -1001
    const val COMMON_INTERNAL_ERROR = -1003
    const val COMMON_NOINTERNET = -1004

    /**
     * APIMANAGER CONSTANTS
     */
    const val AM_RESULT = "result"
    const val AM_PAYLOAD = "payload"
    const val AM_RESPONSE = "response"
    const val AM_ERROR = "error"
}