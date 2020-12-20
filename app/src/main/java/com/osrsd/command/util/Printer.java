package com.osrsd.command.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.osrsd.App;
import com.osrsd.cache.def.SoundEffectDefinition;
import com.osrsd.cache.def.SpriteDefinition;
import com.osrsd.cache.util.Serializable;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;
import java.util.stream.IntStream;

@Slf4j
public class Printer {

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    public static void printContent(final Serializable service) {
        try (FileWriter fileWriter = new FileWriter(String.format("%s/%s.json", String.format("%s/dump", App.defaultPath()), service.getPath()))) {
            gson.toJson(service.getDefinitions(), fileWriter);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static void printImage(final Serializable service) {
        final File file = new File(String.format("%s/dump%s", App.defaultPath(), service.getPath()));
        if (!file.exists() && !file.mkdir()) {
            throw new RuntimeException(String.format("Couldn't create directory at %s.", file.getPath()));
        }
        service.getDefinitions().stream().filter(definition -> definition instanceof SpriteDefinition)
                .forEach(definition -> {
                    SpriteDefinition sprite = (SpriteDefinition) definition;
                    IntStream.range(0, sprite.getFrames().length).forEach(frame -> {
                        try {
                            ImageIO.write(sprite.getFrames()[frame], "png", new File(file.getPath(), String.format("%s_%s.png", sprite.getId(), frame)));
                        } catch (IOException e) {
                            log.error(e.getMessage());
                        }
                    });
                });
    }

    public static void printAudio(final Serializable service) {
        final File file = new File(String.format("%s/dump%s", App.defaultPath(), service.getPath()));
        if (!file.exists() && !file.mkdir()) {
            throw new RuntimeException(String.format("Couldn't create directory at %s.", file.getPath()));
        }
        service.getDefinitions().stream().filter(definition -> definition instanceof SoundEffectDefinition)
                .forEach(definition -> {
                    SoundEffectDefinition soundEffect = (SoundEffectDefinition) definition;
                    byte[] data = soundEffect.mix();
                    AudioFormat audioFormat = new AudioFormat(22050, 8, 1, true, false);
                    AudioInputStream ais = new AudioInputStream(new ByteArrayInputStream(data), audioFormat, data.length);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    try {
                        AudioSystem.write(ais, AudioFileFormat.Type.WAVE, bos);
                        DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(file.getPath(), String.format("%s.wav", soundEffect.getId()))));
                        dos.write(bos.toByteArray());
                        dos.close();
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                });
    }

}
