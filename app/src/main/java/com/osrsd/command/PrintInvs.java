package com.osrsd.command;

import com.displee.cache.CacheLibrary;
import com.osrsd.cache.loader.EnumLoader;
import com.osrsd.cache.loader.InvLoader;
import com.osrsd.command.util.Printer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintInvs implements Runnable {

    private final CacheLibrary cache;

    @Override
    public void run() {
        Printer.print(new InvLoader().load(cache));
    }

}
