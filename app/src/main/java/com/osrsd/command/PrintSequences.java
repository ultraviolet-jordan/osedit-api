package com.osrsd.command;

import com.displee.cache.CacheLibrary;
import com.osrsd.App;
import com.osrsd.cache.loader.SequenceLoader;
import com.osrsd.command.util.Printer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintSequences implements Runnable {

    private final CacheLibrary cache;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        Printer.printContent(new SequenceLoader().load(cache));
        App.prompt(PrintSequences.class, start);
    }

}
