package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintEnums implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Library.cachePrintable(EnumDefinition.class, new EnumLoader().load().getDefinitions());
//        App.prompt(PrintEnums.class, start);
        latch.countDown();
    }

}
