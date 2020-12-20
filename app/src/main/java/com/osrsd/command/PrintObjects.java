package com.osrsd.command;

import com.osrsd.App;
import com.osrsd.cache.Library;
import com.osrsd.cache.loader.ObjectLoader;
import com.osrsd.command.util.Printer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintObjects implements Runnable {

    private final Library library;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        Printer.printContent(new ObjectLoader().load(library));
        App.prompt(PrintObjects.class, start);
    }

}
