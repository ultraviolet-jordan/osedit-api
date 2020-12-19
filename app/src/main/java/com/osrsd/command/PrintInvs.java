package com.osrsd.command;

import com.displee.cache.CacheLibrary;
import com.osrsd.App;
import com.osrsd.cache.loader.InvLoader;
import com.osrsd.command.util.Printer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintInvs implements Runnable {

    private final CacheLibrary cache;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        Printer.printContent(new InvLoader().load(cache));
        App.prompt(PrintInvs.class, start);
    }

}
