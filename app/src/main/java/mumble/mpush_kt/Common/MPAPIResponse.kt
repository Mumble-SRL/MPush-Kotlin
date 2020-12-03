package mumble.mpush_kt.Common

import android.os.Bundle
import java.io.Serializable

/**
 * Identifies a Nooko API response:
 * Result which says if API resulted ok or not
 * Error which, if not null, contains a localized error
 * Payload is a bundle which contains the payload of the api
 *
 * @author Enrico Ori
 * @version {@value MPushConstants#version}
 */
class MPAPIResponse(
    /**
     * The result of the API
     */
    var result: Boolean,
    /**
     * The localized error, if it happened
     */
    val error: String,
    /**
     * API action associated with the API called
     */
    var apiAction: String,
    /**
     * Payload bundle, which contains the objects from the APIs
     */
    var payload: Bundle
) : Serializable