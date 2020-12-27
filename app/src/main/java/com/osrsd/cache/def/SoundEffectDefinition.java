package com.osrsd.cache.def;

import com.osrsd.cache.def.sound.AudioInstrument;
import com.osrsd.spring.domain.Definition;
import lombok.Data;

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

}
