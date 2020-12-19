package com.osrsd.command;

import com.displee.cache.CacheLibrary;
import com.osrsd.App;
import com.osrsd.cache.loader.TextureLoader;
import com.osrsd.command.util.Printer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintTextures implements Runnable {

    private final CacheLibrary cache;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        Printer.printImage(new TextureLoader().load(cache));
        App.prompt(PrintTextures.class, start);
    }

}
