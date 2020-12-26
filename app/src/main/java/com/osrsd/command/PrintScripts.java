package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintScripts implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Library.cachePrintable(ScriptDefinition.class, new ScriptLoader().load().getDefinitions());
//        App.prompt(PrintScripts.class, start);
        latch.countDown();
    }

}
