package com.osrsd.command;

import com.osrsd.App;
import com.osrsd.cache.Library;
import com.osrsd.cache.loader.SequenceLoader;
import com.osrsd.command.util.Printer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintSequences implements Runnable {

    private final Library library;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        Printer.printContent(new SequenceLoader().load(library));
        App.prompt(PrintSequences.class, start);
    }

}
