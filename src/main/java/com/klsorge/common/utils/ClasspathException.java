package com.klsorge.common.utils;

/**
 * Signals that an exception of some sort has occurred while trying to access
 * resources on the classpath. This class is the general class of exceptions
 * produced by failed or interrupted I/O operations.
 * 
 * @author Kurt Sorge
 * 
 */
public class ClasspathException extends RuntimeException {

    private static final long serialVersionUID = -2078143207810195542L;

    /**
     * Constructs a {@code ClasspathException} with {@code null} as its error
     * detail message.
     */
    public ClasspathException() {
        super();
    }

    /**
     * Constructs a {@code ClasspathException} with the specified detail
     * message.
     * 
     * @param message
     *            The detail message. The detail message is saved for later
     *            retrieval by the {@link #getMessage()} method.
     */
    public ClasspathException(final String message) {
        super(message);
    }

    /**
     * Constructs a {@code ClasspathException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())} (which
     * typically contains the class and detail message of cause). This
     * constructor is useful for classpath exceptions that are little more than
     * wrappers for other throwables.
     * 
     * @param cause
     *            The cause (which is saved for later retrieval by the
     *            {@link #getCause()} method). (A {@code null} value is
     *            permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     */
    public ClasspathException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code ClasspathException} with the specified detail message
     * and cause.
     * 
     * Note that the detail message associated with cause is not automatically
     * incorporated into this exception's detail message.
     * 
     * @param message
     *            The detail message. The detail message is saved for later
     *            retrieval by the {@link #getMessage()} method.
     * @param cause
     *            The cause (which is saved for later retrieval by the
     *            {@link #getCause()} method). (A {@code null} value is
     *            permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     */
    public ClasspathException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
