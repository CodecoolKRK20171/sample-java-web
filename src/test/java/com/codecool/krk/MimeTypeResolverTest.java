package com.codecool.krk;

import com.codecool.krk.helpers.MimeTypeResolver;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MimeTypeResolverTest {
    @Test
    public void testGetFileExtension(){
        File file = mock(File.class);
        when(file.getName()).thenReturn("style.css");
        MimeTypeResolver resolver = new MimeTypeResolver(file);
        assertEquals("css", resolver.getFileExtension());
    }

    @Test
    public void testGetMimeType(){
        File file = mock(File.class);
        when(file.getName()).thenReturn("style.css");
        MimeTypeResolver resolver = new MimeTypeResolver(file);
        assertEquals("text/css", resolver.getMimeType());
    }

}
