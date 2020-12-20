package com.osrsd.command;

import com.osrsd.App;
import com.osrsd.cache.Library;
import com.osrsd.cache.loader.ParamLoader;
import com.osrsd.command.util.Printer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintParams implements Runnable {

    private final Library library;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        Printer.printContent(new ParamLoader().load(library));
        App.prompt(PrintParams.class, start);
    }

}
