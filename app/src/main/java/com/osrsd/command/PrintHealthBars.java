package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintHealthBars implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Library.cachePrintable(HealthbarDefinition.class, new HealthBarLoader().load().getDefinitions());
//        App.prompt(PrintHealthBars.class, start);
        latch.countDown();
    }

}
