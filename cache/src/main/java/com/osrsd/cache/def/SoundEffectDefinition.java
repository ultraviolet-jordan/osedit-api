package com.osrsd.cache.def;

import com.osrsd.cache.def.sound.AudioInstrument;
import lombok.Data;

import java.nio.ByteBuffer;

@Data
public final class SoundEffectDefinition implements Definition {

    private int id;
    private AudioInstrument[] instruments;
    private int start;
    private int end;

    public SoundEffectDefinition(int id) {
        this.id = id;
        instruments = new AudioInstrument[10];
    }

    @Override
    public void decode(ByteBuffer buffer) {
        for (int var2 = 0; var2 < 10; ++var2) {
            int var3 = buffer.get() & 0xff;
            if (var3 != 0) {
                buffer.position(buffer.position() - 1);
                instruments[var2] = new AudioInstrument();
                instruments[var2].decode(buffer);
            }
        }
        start = buffer.getShort() & 0xffff;
        end = buffer.getShort() & 0xffff;
    }

    public final byte[] mix() {
        int var1 = 0;
        int var2;
        for (var2 = 0; var2 < 10; ++var2) {
            if (this.instruments[var2] != null && this.instruments[var2].getDuration() + this.instruments[var2].getOffset() > var1) {
                var1 = this.instruments[var2].getDuration() + this.instruments[var2].getOffset();
            }
        }
        if (var1 == 0) {
            return new byte[0];
        } else {
            var2 = var1 * 22050 / 1000;
            byte[] var3 = new byte[var2];
            for (int index = 0; index < 10; ++index) {
                if (this.instruments[index] != null) {
                    int var5 = this.instruments[index].getDuration() * 22050 / 1000;
                    int var6 = this.instruments[index].getOffset() * 22050 / 1000;
                    int[] var7 = this.instruments[index].synthesize(var5, this.instruments[index].getDuration());
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
