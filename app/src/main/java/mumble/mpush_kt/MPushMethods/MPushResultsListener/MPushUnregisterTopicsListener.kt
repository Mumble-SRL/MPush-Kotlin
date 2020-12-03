package mumble.mpush_kt.MPushMethods.MPushResultsListener

/**
 * Interface to use with [(Context)][mumble.mpush_kt.MPushMethods.MPushAsyncTasks.MPushAsyncTask_UnregisterTopics], and similar, methods,
 * send the push token
 *
 * @author Enrico Ori
 * @version {@value MPushConstants#version}
 */
interface MPushUnregisterTopicsListener {
    fun onTopicsUnregistered()
    fun onTopicsUnregisteredError(error: String?)
}