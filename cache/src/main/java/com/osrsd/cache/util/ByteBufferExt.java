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

    public static int getShortSmart(ByteBuffer buf) {
        int peek = buf.get(buf.position()) & 0xff;
        return peek < 128 ? (buf.get() & 0xff) - 64 : (buf.getShort() & 0xffff) - 0xc000;
    }

    public static int getUnsignedShortSmart(ByteBuffer buf) {
        int peek = buf.get(buf.position()) & 0xff;
        return peek < 128 ? (buf.get() & 0xff) : (buf.getShort() & 0xffff) - 0x8000;
    }

}
