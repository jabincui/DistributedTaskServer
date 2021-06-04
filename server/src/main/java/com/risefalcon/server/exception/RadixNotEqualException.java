package com.risefalcon.server.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RadixNotEqualException extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public RadixNotEqualException() {
        super();
        log.error("Comparing two atom tasks with different radixes is forbidden.");
    }
}
