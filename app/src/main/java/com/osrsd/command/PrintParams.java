package com.osrsd.command;

import com.displee.cache.CacheLibrary;
import com.osrsd.App;
import com.osrsd.cache.loader.EnumLoader;
import com.osrsd.cache.loader.ParamLoader;
import com.osrsd.command.util.Printer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintParams implements Runnable {

    private final CacheLibrary cache;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        Printer.printContent(new ParamLoader().load(cache));
        App.prompt(PrintParams.class, start);
    }

}
