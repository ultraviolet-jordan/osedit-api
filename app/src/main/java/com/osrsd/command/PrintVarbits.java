package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintVarbits implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Library.cachePrintable(VarbitDefinition.class, new VarbitLoader().load().getDefinitions());
//        App.prompt(PrintVarbits.class, start);
        latch.countDown();
    }

}
