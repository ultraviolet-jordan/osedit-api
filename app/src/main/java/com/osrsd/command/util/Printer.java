package com.osrsd.command.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.osrsd.App;
import com.osrsd.cache.def.SpriteDefinition;
import com.osrsd.cache.util.Serializable;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.IntStream;

public class Printer {

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    public static void printContent(final Serializable service) {
        try (FileWriter fileWriter = new FileWriter(String.format("%s/%s.json", String.format("%s/dump", App.defaultPath()), service.getPath()))) {
            gson.toJson(service.getDefinitions(), fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printImage(final Serializable service) {
        final File file = new File(String.format("%s/dump%s", App.defaultPath(), service.getPath()));
        if (!file.exists()) {
            if (!file.mkdir()) {
                throw new RuntimeException(String.format("Couldn't create directory at %s.", file.getPath()));
            }
        }
        service.getDefinitions().stream().filter(definition -> definition instanceof SpriteDefinition)
                .forEach(definition -> {
                    SpriteDefinition sprite = (SpriteDefinition) definition;
                    IntStream.range(0, sprite.getFrames().length).forEach(frame -> {
                        try {
                            ImageIO.write(sprite.getFrames()[frame], "png", new File(file.getPath(), String.format("%s_%s.png", sprite.getId(), frame)));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                });
    }

}
