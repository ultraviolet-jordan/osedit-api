package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintAreas implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Library.INSTANCE.cachePrintable(AreaDefinition.class, new AreaLoader().load().getDefinitions());
//        App.prompt(PrintAreas.class, start);
        latch.countDown();
    }

}
