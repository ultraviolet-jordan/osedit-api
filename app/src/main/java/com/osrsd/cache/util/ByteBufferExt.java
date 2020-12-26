package com.osrsd.cache.util;

import java.nio.ByteBuffer;

public final class ByteBufferExt {

    public static int getMedium(ByteBuffer buffer) {
        return ((buffer.get() & 0xff) << 16) | ((buffer.get() & 0xff) << 8) | (buffer.get() & 0xff);
    }

    public static String getString(ByteBuffer buffer) {
        StringBuilder builder = new StringBuilder();
        int b;
        while ((b = (buffer.get() & 0xff)) != 0) {
            builder.append((char) b);
        }
        return builder.toString();
    }

    public static String getStringOrNull(ByteBuffer buffer) {
        int peek = buffer.get(buffer.position()) & 0xff;
        if (peek != 0) {
            return getString(buffer);
        } else {
            buffer.get(); // discard
            return null;
        }
    }

    public static int getShortSmart(ByteBuffer buffer) {
        int peek = buffer.get(buffer.position()) & 0xff;
        return peek < 128 ? (buffer.get() & 0xff) - 64 : (buffer.getShort() & 0xffff) - 0xc000;
    }

    public static int getUnsignedShortSmart(ByteBuffer buffer) {
        int peek = buffer.get(buffer.position()) & 0xff;
        return peek < 128 ? (buffer.get() & 0xff) : (buffer.getShort() & 0xffff) - 0x8000;
    }

}
