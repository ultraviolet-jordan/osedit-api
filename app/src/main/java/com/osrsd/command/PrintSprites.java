package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintSprites implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Printer.printImage(new SpriteLoader().load());
//        App.prompt(PrintSprites.class, start);
        latch.countDown();
    }

}
