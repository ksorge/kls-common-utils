package com.klsorge.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities to access and manipulate resources on the classpath.
 * 
 * @author Kurt Sorge
 * 
 */
public final class ClasspathUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ClasspathUtils.class);

    /**
     * Returns the default {@code ClassLoader} for this context. The default
     * ClassLoader is the context ClassLoader of the current {@code Thread}. If
     * there is a problem accessing the Thread's ClassLoader the system
     * ClassLoader is returned instead.
     * 
     * @return The default {@code ClassLoader} or {@code null} if none.
     * 
     * @see Thread#getContextClassLoader()
     * @see ClassLoader#getSystemClassLoader()
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader loader = null;
        try {
            loader = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            LOG.warn("error while loading thread classloader: " + ex.getMessage());
        }
        if (null == loader) {
            loader = ClassLoader.getSystemClassLoader();
        }
        return loader;
    }

    /**
     * Indicates if the resource with the given name exists on the classpath.
     * 
     * @param name
     *            The resource name.
     * @return {@code true} if the resource could be found and the invoker has
     *         adequate privileges to get the resource.
     */
    public static boolean hasResource(final String name) {
        return null != getResource(name);
    }

    /**
     * Finds the resource with the given name. A resource is some data (images,
     * audio, text, etc) that can be accessed by class code in a way that is
     * independent of the location of the code.
     * 
     * The name of a resource is a '/'-separated path name that identifies the
     * resource.
     * 
     * @param name
     *            The resource name.
     * @return A {@code URL} object for reading the resource, or {@code null} if
     *         the resource could not be found or the invoker doesn't have
     *         adequate privileges to get the resource.
     */
    public static URL getResource(final String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        ClassLoader loader = getDefaultClassLoader();
        return loader.getResource(name);

    }

    /**
     * Returns an input stream for reading the specified resource.
     * 
     * @param name
     *            The resource name.
     * @return An input stream for reading the resource.
     * @throws ResourceNotFoundException
     *             if the resource cannot be found on the classpath.
     */
    public static InputStream getResourceAsStream(final String name) {
        if (!hasResource(name)) {
            throw new ResourceNotFoundException("cannot find " + name + " on classpath");
        }
        return getDefaultClassLoader().getResourceAsStream(name);
    }

    /**
     * Returns the contents of a resource as an array of bytes.
     * 
     * @param name
     *            The resource name.
     * @return A byte array of the resource's contents.
     * @throws ResourceNotFoundException
     *             if the resource cannot be found on the classpath.
     * @throws ClasspathException
     *             if the contents cannot be extracted from the resource.
     */
    public static byte[] getResourceAsBytes(final String name) {
        try {
            InputStream in = getResourceAsStream(name);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IOUtils.copy(in, out);
            return out.toByteArray();
        }
        catch (IOException e) {
            String message = String.format("could not convert %s to byte array: %s", name, e.getMessage());
            throw new ClasspathException(message, e);
        }
    }

    /**
     * Return the contents of a resource as a {@code String} using the specified
     * character encoding.
     * 
     * @param name
     *            The resource name.
     * @param charset
     *            The character set to use, {@code null} means platform default.
     * @return return the requested {@code String}.
     * @throws ResourceNotFoundException
     *             if the resource cannot be found on the classpath.
     * @throws ClasspathException
     *             if the contents cannot be extracted from the resource.
     */
    public static String getResourceAsString(final String name, final Charset charset) {
        byte[] bytes = getResourceAsBytes(name);
        return new String(bytes, charset);
    }

    /**
     * Return the contents of a resource as a {@code String} using the default
     * character encoding of the platform.
     * 
     * @param name
     *            The resource name.
     * @return return the requested {@code String}.
     * @throws ResourceNotFoundException
     *             if the resource cannot be found on the classpath.
     * @throws ClasspathException
     *             if the contents cannot be extracted from the resource.
     */
    public static String getResourceAsString(final String name) {
        return getResourceAsString(name, Charset.defaultCharset());
    }

    private ClasspathUtils() {
    }
}
