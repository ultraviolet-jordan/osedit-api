package com.osrsd.command.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.osrsd.App;
import com.osrsd.cache.util.Serializable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Printer {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void print(final Serializable service) {
        final File file = new File(String.format("%s/dump", App.defaultPath()));

        if (!file.exists()) {
            if (!file.mkdir()) {
                throw new RuntimeException(String.format("Couldn't create directory at %s.", file.getPath()));
            }
        }
        try (FileWriter fileWriter = new FileWriter(String.format("%s/%s.json", file.getPath(), service.getPath()))) {
            gson.toJson(service.getDefinitions(), fileWriter);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }

}
