package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintSpotAnimations implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Library.cachePrintable(SpotAnimationDefinition.class, new SpotAnimationLoader().load().getDefinitions());
//        App.prompt(PrintSpotAnimations.class, start);
        latch.countDown();
    }

}
