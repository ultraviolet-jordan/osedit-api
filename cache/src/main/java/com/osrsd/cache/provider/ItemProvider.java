package com.osrsd.cache.provider;

import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.ItemDefinition;
import com.osrsd.cache.util.ByteBufferExt;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.stream.IntStream;

@Slf4j
public final class ItemProvider {

    public static Definition decode(ByteBuffer buffer, ItemDefinition definition) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                definition.setInventoryModel(buffer.getShort() & 0xffff);
            } else if (opcode == 2) {
                definition.setName(ByteBufferExt.getString(buffer));
            } else if (opcode == 4) {
                definition.setZoom2d(buffer.getShort() & 0xffff);
            } else if (opcode == 5) {
                definition.setXan2d(buffer.getShort() & 0xffff);
            } else if (opcode == 6) {
                definition.setYan2d(buffer.getShort() & 0xffff);
            } else if (opcode == 7) {
                definition.setXOffset2d(buffer.getShort() & 0xffff);
                if (definition.getXOffset2d() > Short.MAX_VALUE) {
                    definition.setXOffset2d(definition.getXOffset2d() - 65536);
                }
            } else if (opcode == 8) {
                definition.setYOffset2d(buffer.getShort() & 0xffff);
                if (definition.getYOffset2d() > Short.MAX_VALUE) {
                    definition.setYOffset2d(definition.getYOffset2d() - 65536);
                }
            } else if (opcode == 11) {
                definition.setStackable(1);
            } else if (opcode == 12) {
                definition.setCost(buffer.getInt());
            } else if (opcode == 16) {
                definition.setMembers(true);
            } else if (opcode == 23) {
                definition.setMaleModel0(buffer.getShort() & 0xffff);
                definition.setMaleOffset(buffer.get() & 0xff);
            } else if (opcode == 24) {
                definition.setMaleModel1(buffer.getShort() & 0xffff);
            } else if (opcode == 25) {
                definition.setFemaleModel0(buffer.getShort() & 0xffff);
                definition.setFemaleOffset(buffer.get() & 0xff);
            } else if (opcode == 26) {
                definition.setFemaleModel1(buffer.getShort() & 0xffff);
            } else if (opcode >= 30 && opcode < 35) {
                definition.getOptions()[opcode - 30] = ByteBufferExt.getString(buffer);
                if (definition.getOptions()[opcode - 30].equalsIgnoreCase("Hidden")) {
                    definition.getOptions()[opcode - 30] = null;
                }
            } else if (opcode >= 35 && opcode < 40) {
                definition.getInterfaceOptions()[opcode - 35] = ByteBufferExt.getString(buffer);
            } else if (opcode == 40) {
                int var5 = buffer.get() & 0xff;
                definition.setColorFind(new short[var5]);
                definition.setColorReplace(new short[var5]);
                IntStream.range(0, var5).forEach(var4 -> {
                    definition.getColorFind()[var4] = (short) (buffer.getShort() & 0xffff);
                    definition.getColorReplace()[var4] = (short) (buffer.getShort() & 0xffff);
                });
            } else if (opcode == 41) {
                int var5 = buffer.get() & 0xff;
                definition.setTextureFind(new short[var5]);
                definition.setTextureReplace(new short[var5]);
                IntStream.range(0, var5).forEach(var4 -> {
                    definition.getTextureFind()[var4] = (short) (buffer.getShort() & 0xffff);
                    definition.getTextureReplace()[var4] = (short) (buffer.getShort() & 0xffff);
                });
            } else if (opcode == 42) {
                definition.setShiftClickDropIndex(buffer.get());
            } else if (opcode == 65) {
                definition.setTradeable(true);
            } else if (opcode == 78) {
                definition.setMaleModel2(buffer.getShort() & 0xffff);
            } else if (opcode == 79) {
                definition.setFemaleModel2(buffer.getShort() & 0xffff);
            } else if (opcode == 90) {
                definition.setMaleHeadModel(buffer.getShort() & 0xffff);
            } else if (opcode == 91) {
                definition.setFemaleHeadModel(buffer.getShort() & 0xffff);
            } else if (opcode == 92) {
                definition.setMaleHeadModel2(buffer.getShort() & 0xffff);
            } else if (opcode == 93) {
                definition.setFemaleHeadModel2(buffer.getShort() & 0xffff);
            } else if (opcode == 95) {
                definition.setZan2d(buffer.getShort() & 0xffff);
            } else if (opcode == 97) {
                definition.setNotedID(buffer.getShort() & 0xffff);
            } else if (opcode == 98) {
                definition.setNotedTemplate(buffer.getShort() & 0xffff);
            } else if (opcode >= 100 && opcode < 110) {
                if (definition.getCountObj() == null) {
                    definition.setCountObj(new int[10]);
                    definition.setCountCo(new int[10]);
                }
                definition.getCountObj()[opcode - 100] = buffer.getShort() & 0xffff;
                definition.getCountCo()[opcode - 100] = buffer.getShort() & 0xffff;
            } else if (opcode == 110) {
                definition.setResizeX(buffer.getShort() & 0xffff);
            } else if (opcode == 111) {
                definition.setResizeY(buffer.getShort() & 0xffff);
            } else if (opcode == 112) {
                definition.setResizeZ(buffer.getShort() & 0xffff);
            } else if (opcode == 113) {
                definition.setAmbient(buffer.get());
            } else if (opcode == 114) {
                definition.setContrast(buffer.get());
            } else if (opcode == 115) {
                definition.setTeam(buffer.get() & 0xff);
            } else if (opcode == 139) {
                definition.setBoughtId(buffer.getShort() & 0xffff);
            } else if (opcode == 140) {
                definition.setBoughtTemplateId(buffer.getShort() & 0xffff);
            } else if (opcode == 148) {
                definition.setPlaceholderId(buffer.getShort() & 0xffff);
            } else if (opcode == 149) {
                definition.setPlaceholderTemplateId(buffer.getShort() & 0xffff);
            } else if (opcode == 249) {
                int length = buffer.get() & 0xff;
                for (int i = 0; i < length; i++) {
                    boolean isString = buffer.get() == 1;
                    int key = ByteBufferExt.getMedium(buffer);
                    Object value;
                    if (isString) {
                        value = ByteBufferExt.getString(buffer);
                    } else {
                        value = buffer.getInt();
                    }
                    definition.getParams().put(key, value);
                }
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
        return definition;
    }

}
