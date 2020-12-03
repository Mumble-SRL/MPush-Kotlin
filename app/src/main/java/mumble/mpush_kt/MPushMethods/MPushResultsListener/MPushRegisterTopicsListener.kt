package mumble.mpush_kt.MPushMethods.MPushResultsListener

/**
 * Interface to use with [(Context)][mumble.mpush_kt.MPushMethods.MPushAsyncTasks.MPushAsyncTask_RegisterTopics], and similar, methods,
 * send the push token
 *
 * @author Enrico Ori
 * @version {@value MPushConstants#version}
 */
interface MPushRegisterTopicsListener {
    fun onTopicsRegistered()
    fun onTopicsRegisteredError(error: String?)
}