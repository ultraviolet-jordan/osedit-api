package com.osrsd.command;

import com.osrsd.App;
import com.osrsd.cache.Library;
import com.osrsd.cache.loader.EnumLoader;
import com.osrsd.cache.loader.HealthBarLoader;
import com.osrsd.command.util.Printer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintHealthBars implements Runnable {

    private final Library library;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        Printer.printContent(new HealthBarLoader().load(library));
        App.prompt(PrintHealthBars.class, start);
    }

}
