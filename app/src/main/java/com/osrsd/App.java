package com.osrsd;

import com.osrsd.cache.Library;
import com.osrsd.command.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
        log.info("Executing...");
        final File file = new File(String.format("%s/dump", defaultPath()));

        if (!file.exists() && !file.mkdir()) {
            throw new RuntimeException(String.format("Couldn't create directory at %s.", file.getPath()));
        }
        init();
    }

    private static void init() {
        final Library library = new Library(defaultPath());
        final List<Runnable> commands = List.of(
                new PrintInvs(library),
                new PrintObjects(library),
                new PrintEnums(library),
                new PrintNpcs(library),
                new PrintItems(library),
                new PrintParams(library),
                new PrintSequences(library),
                new PrintSpotAnimations(library),
                new PrintVarbits(library),
                new PrintSoundEffects(library),
                new PrintSprites(library),
                new PrintTextures(library)
        );

        int cores = Runtime.getRuntime().availableProcessors();
        if (cores > 2) {
            final ExecutorService pool = Executors.newFixedThreadPool(cores);
            commands.forEach(pool::execute);
            pool.shutdown();
        } else {
            commands.forEach(Runnable::run);
        }
    }

    public static String defaultPath() {
        return String.format("./%s", properties.getProperty("cache.version"));
    }

    public static void prompt(Class<? extends Runnable> command, long start) {
        log.info(String.format("%s took %sms to dump.", command.getSimpleName(), System.currentTimeMillis() - start));
    }

}
