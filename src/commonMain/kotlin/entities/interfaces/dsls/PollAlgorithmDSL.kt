package entities.interfaces.dsls

import entities.interfaces.PollAlgorithmParameter

/**
 * DSL for poll algorithms.
 */
interface PollAlgorithmDSL {

    /**
     * List of parameters.
     */
    var pollAlgorithmParameters: List<PollAlgorithmParameter>

    /**
     * Shortcut useful to add an element in [pollAlgorithmParameters].
     */
    operator fun PollAlgorithmParameter.unaryPlus() {
        pollAlgorithmParameters += this@unaryPlus
    }
}
