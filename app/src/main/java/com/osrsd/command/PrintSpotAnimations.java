package com.osrsd.command;

import com.displee.cache.CacheLibrary;
import com.osrsd.cache.loader.SpotAnimationLoader;
import com.osrsd.command.util.Printer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintSpotAnimations implements Runnable {

    private final CacheLibrary cache;

    @Override
    public void run() {
        Printer.print(new SpotAnimationLoader().load(cache));
    }

}