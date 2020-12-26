//package com.osrsd.command;
//
//import com.osrsd.App;
//import com.osrsd.cache.Library;
//import com.osrsd.cache.loader.ItemLoader;
//import com.osrsd.spring.domain.ItemDefinition;
//import lombok.AllArgsConstructor;
//
//import java.util.concurrent.CountDownLatch;
//
//@AllArgsConstructor
//public class PrintItems implements Runnable {
//
//    private final CountDownLatch latch;
//
//    @Override
//    public void run() {
//        long start = System.currentTimeMillis();
//        Library.cachePrintable(ItemDefinition.class, new ItemLoader().load().getDefinitions());
//        App.prompt(PrintItems.class, start);
//        latch.countDown();
//    }
//
//}
