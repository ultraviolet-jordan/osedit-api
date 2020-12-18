package com.osrsd;

import com.displee.cache.CacheLibrary;
import com.osrsd.command.PrintSequences;
import com.osrsd.command.PrintSpotAnimations;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class App {

    private static final Properties properties = new Properties();

    static {
        try (InputStream in = App.class.getResourceAsStream("/application.properties")) {
            properties.load(in);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String... args) {
        long start = System.currentTimeMillis();
        log.info("Executing application...");

        final CacheLibrary library = CacheLibrary.create(defaultPath());
        final List<Runnable> commands = Arrays.asList(
                new PrintSequences(library),
                new PrintSpotAnimations(library)
        );

        int cores = Runtime.getRuntime().availableProcessors();
        if (cores == 1) {
            commands.forEach(Runnable::run);
            log.info(performance(start));
        } else {
            final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            commands.forEach(pool::execute);
            pool.shutdown();

            do {
                log.info(performance(start));
            } while (pool.isTerminated());
        }
    }

    public static String defaultPath() {
        return String.format("./%s", properties.getProperty("cache.version"));
    }

    private static String performance(long start) {
        return String.format("Took %sms to dump.", System.currentTimeMillis() - start);
    }

}
