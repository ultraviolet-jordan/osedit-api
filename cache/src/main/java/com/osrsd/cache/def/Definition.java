package com.osrsd.cache.def;

import java.nio.ByteBuffer;

public interface Definition {
    void decode(ByteBuffer buffer);
}
