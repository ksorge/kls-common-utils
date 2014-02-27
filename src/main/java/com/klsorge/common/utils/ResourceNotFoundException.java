package com.klsorge.common.utils;

/**
 * resource not found.
 * @author kurt
 *
 */
public class ResourceNotFoundException extends ClasspathException {

    private static final long serialVersionUID = -5889625534387318507L;

    /**
     * default constructor.
     */
    public ResourceNotFoundException() {
        super();
    }

    /**
     * constructor.
     * @param message the messge.
     * @param cause the cause.
     */
    public ResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * constructor.
     * @param message the message.
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }

    /**
     * constructor.
     * @param cause the cause.
     */
    public ResourceNotFoundException(final Throwable cause) {
        super(cause);
    }

}
