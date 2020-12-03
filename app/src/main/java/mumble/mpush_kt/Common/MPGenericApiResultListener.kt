package mumble.mpush_kt.Common

/**
 * Interface to retrieve MPush Api result, must be implemented in Activities or Fragments
 * which are working with the API
 */
interface MPGenericApiResultListener {
    fun onApiResult(response: MPAPIResponse?)
}