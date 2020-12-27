package com.osrsd.cache.provider;

import com.osrsd.cache.def.SoundEffectDefinition;
import com.osrsd.cache.def.sound.AudioInstrument;
import com.osrsd.spring.domain.Definition;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public final class SoundEffectProvider {

    public static Definition decode(ByteBuffer buffer, SoundEffectDefinition definition) {
        for (int var2 = 0; var2 < 10; ++var2) {
            int var3 = buffer.get() & 0xff;
            if (var3 != 0) {
                buffer.position(buffer.position() - 1);
                definition.getInstruments()[var2] = new AudioInstrument();
                definition.getInstruments()[var2].decode(buffer);
            }
        }
        definition.setStart(buffer.getShort() & 0xffff);
        definition.setEnd(buffer.getShort() & 0xffff);
        return definition;
    }

    public static byte[] mix(SoundEffectDefinition definition) {
        int var1 = 0;
        int var2;
        for (var2 = 0; var2 < 10; ++var2) {
            if (definition.getInstruments()[var2] != null && definition.getInstruments()[var2].getDuration() + definition.getInstruments()[var2].getOffset() > var1) {
                var1 = definition.getInstruments()[var2].getDuration() + definition.getInstruments()[var2].getOffset();
            }
        }
        if (var1 == 0) {
            return new byte[0];
        } else {
            var2 = var1 * 22050 / 1000;
            byte[] var3 = new byte[var2];
            for (int index = 0; index < 10; ++index) {
                if (definition.getInstruments()[index] != null) {
                    int var5 = definition.getInstruments()[index].getDuration() * 22050 / 1000;
                    int var6 = definition.getInstruments()[index].getOffset() * 22050 / 1000;
                    int[] var7 = definition.getInstruments()[index].synthesize(var5, definition.getInstruments()[index].getDuration());
                    for (int var8 = 0; var8 < var5; ++var8) {
                        int var9 = (var7[var8] >> 8) + var3[var8 + var6];
                        if ((var9 + 128 & -256) != 0) {
                            var9 = var9 >> 31 ^ 127;
                        }
                        var3[var8 + var6] = (byte) var9;
                    }
                }
            }
            return var3;
        }
    }

}
