package com.roddet.agent.simple;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.net.URISyntaxException;
import java.security.ProtectionDomain;

public class MySimpleTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {
        if (s.equals("com/roddet/swing/app/SwingFrame")) {
            try {
                bytes = getSwingFrameModifiedByteCode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    private byte[] getSwingFrameModifiedByteCode() throws IOException, URISyntaxException {
        InputStream fileInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SwingFrame.class");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while (-1 != (n = fileInputStream.read(buffer))) {
            output.write(buffer, 0, n);
        }
        fileInputStream.close();
        return output.toByteArray();
    }
}
