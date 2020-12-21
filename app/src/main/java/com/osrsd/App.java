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
public final class App {

    /**
     * Application properties.
     */
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
        final File base = new File(baseDirectory());
        if (!base.exists() && !base.mkdir()) {
            throw new RuntimeException(String.format("Could not create directory at %s.", base.getPath()));
        }

        final File dump = new File(String.format("%s/dump", baseDirectory()));
        if (!dump.exists() && !dump.mkdir()) {
            throw new RuntimeException(String.format("Could not create directory at %s.", dump.getPath()));
        }

        //App really starts here.
        init();
    }

    /**
     * The main process.
     */
    private static void init() {
        final Library library = new Library(baseDirectory());
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
                new PrintTextures(library),
                new PrintHealthBars(library),
                new PrintAreas(library),
                new PrintScripts(library)
        );

        int cores = Runtime.getRuntime().availableProcessors();
        //Use parallel execution if the machine has more than 4 cores.
        if (cores > 4) {
            final ExecutorService pool = Executors.newFixedThreadPool(cores);
            commands.forEach(pool::execute);
            pool.shutdown();
        } else {
            commands.forEach(Runnable::run);
        }
    }

    /**
     * The base directory for serializing data.
     *
     * @return The directory as a {@link String}.
     */
    public static String baseDirectory() {
        return String.format("./%s", properties.getProperty("cache.version"));
    }

    /**
     * Prompts the application console with performance numbers.
     *
     * @param command The command prompting the application.
     * @param start   The start time of the command.
     */
    public static void prompt(Class<? extends Runnable> command, long start) {
        log.info(String.format("%s took %sms to dump.", command.getSimpleName(), System.currentTimeMillis() - start));
    }

}
