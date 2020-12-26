package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintParams implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Library.cachePrintable(ParamDefinition.class, new ParamLoader().load().getDefinitions());
//        App.prompt(PrintParams.class, start);
        latch.countDown();
    }

}
