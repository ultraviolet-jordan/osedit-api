package com.osrsd.cache.util;

import java.nio.ByteBuffer;

public final class ByteBufferExt {

    public static int getMedium(ByteBuffer buffer) {
        return ((buffer.get() & 0xFF) << 16) | ((buffer.get() & 0xFF) << 8) | (buffer.get() & 0xFF);
    }

}
