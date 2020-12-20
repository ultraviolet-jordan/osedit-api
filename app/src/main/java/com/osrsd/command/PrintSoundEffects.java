package com.osrsd.command;

import com.osrsd.App;
import com.osrsd.cache.Library;
import com.osrsd.cache.loader.SoundEffectLoader;
import com.osrsd.command.util.Printer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintSoundEffects implements Runnable {

    private final Library library;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        Printer.printAudio(new SoundEffectLoader().load(library));
        App.prompt(PrintSoundEffects.class, start);
    }

}
