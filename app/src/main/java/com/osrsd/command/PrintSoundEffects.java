package com.osrsd.command;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class PrintSoundEffects implements Runnable {

    private final CountDownLatch latch;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        Printer.printAudio(new SoundEffectLoader().load());
//        App.prompt(PrintSoundEffects.class, start);
        latch.countDown();
    }

}
