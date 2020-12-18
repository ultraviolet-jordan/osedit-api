package com.osrsd.cache.util;

import java.nio.ByteBuffer;

public final class ByteBufferExt {

    public static int getMedium(ByteBuffer buffer) {
        return ((buffer.get() & 0xFF) << 16) | ((buffer.get() & 0xFF) << 8) | (buffer.get() & 0xFF);
    }

    public static String getString(ByteBuffer buf) {
        StringBuilder builder = new StringBuilder();
        int b;
        while ((b = (buf.get() & 0xff)) != 0) {
            builder.append((char) b);
        }
        return builder.toString();
    }

}
