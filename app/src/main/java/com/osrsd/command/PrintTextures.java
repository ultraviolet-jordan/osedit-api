package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintTextures implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Printer.printImage(new TextureLoader().load());
//        App.prompt(PrintTextures.class, start);
        latch.countDown();
    }

}
