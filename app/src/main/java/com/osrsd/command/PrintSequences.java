package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintSequences implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Library.cachePrintable(SequenceDefinition.class, new SequenceLoader().load().getDefinitions());
//        App.prompt(PrintSequences.class, start);
        latch.countDown();
    }

}
