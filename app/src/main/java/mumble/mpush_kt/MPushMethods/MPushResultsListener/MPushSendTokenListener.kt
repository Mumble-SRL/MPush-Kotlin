package mumble.mpush_kt.MPushMethods.MPushResultsListener

/**
 * Interface to use with [(Context)][mumble.mpush_kt.MPushMethods.MPushAsyncTasks.MPushAsyncTask_SendToken], and similar, methods,
 * send the push token
 *
 * @author Enrico Ori
 * @version {@value MPushConstants#version}
 */
interface MPushSendTokenListener {
    fun onTokenSent()
    fun onTokenSentError(error: String?)
}