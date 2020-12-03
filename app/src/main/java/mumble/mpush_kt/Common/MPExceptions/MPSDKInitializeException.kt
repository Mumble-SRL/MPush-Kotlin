package mumble.mpush_kt.Common.MPExceptions

/**
 * Exception called when doing an operation before SDK initilization (put in API KEY).
 *
 * @author Enrico Ori
 * @version {@value MPushConstants#version}
 */
class MPSDKInitializeException(message: String?) : RuntimeException(message)