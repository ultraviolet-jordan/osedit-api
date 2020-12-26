package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintInvs implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Library.cachePrintable(InvDefinition.class, new InvLoader().load().getDefinitions());
//        App.prompt(PrintInvs.class, start);
        latch.countDown();
    }

}
