package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintNpcs implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Library.cachePrintable(NpcDefinition.class, new NpcLoader().load().getDefinitions());
//        App.prompt(PrintNpcs.class, start);
        latch.countDown();
    }

}
