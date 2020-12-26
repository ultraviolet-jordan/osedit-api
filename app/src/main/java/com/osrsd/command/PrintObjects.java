package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintObjects implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Library.cachePrintable(ObjectDefinition.class, new ObjectLoader().load().getDefinitions());
//        App.prompt(PrintObjects.class, start);
        latch.countDown();
    }

}
