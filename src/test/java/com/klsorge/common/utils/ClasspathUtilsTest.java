package com.klsorge.common.utils;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.net.URL;

import org.junit.Test;


public class ClasspathUtilsTest {
    
    @Test
    public void testGetResourceWithRealResource() {
        URL actual = ClasspathUtils.getResource("real-resource.txt");
        assertNotNull(actual);
    }
    
    @Test
    public void testGetResourceWithResourceNotExist() {
        URL actual = ClasspathUtils.getResource("not-a-resouce.txt");
        assertNull(actual);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetResourceWtihNullName() {
        ClasspathUtils.getResource(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testGetResourceWithBlankName() {
        ClasspathUtils.getResource("");
    }

    @Test
    public void testHasResourceWithRealResource() {
        boolean actual = ClasspathUtils.hasResource("real-resource.txt");
        assertTrue(actual);
    }
    
    @Test
    public void testHasResourceWithResourceNotExist() {
        boolean actual = ClasspathUtils.hasResource("not-a-resouce.txt");
        assertFalse(actual);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testHasResourceWtihNullName() {
        ClasspathUtils.hasResource(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testHasResourceWithBlankName() {
        ClasspathUtils.hasResource("");
    }

    @Test
    public void testGetResourceAsStreamWithRealResouce() {
        InputStream actual = ClasspathUtils.getResourceAsStream("real-resource.txt");
    }
}
