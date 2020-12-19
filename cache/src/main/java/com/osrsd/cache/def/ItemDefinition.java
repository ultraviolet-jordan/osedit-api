package com.osrsd.cache.def;

import com.osrsd.cache.util.ByteBufferExt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Data
@Slf4j
public final class ItemDefinition implements Definition {

    private int id;
    private String name;
    private int resizeX;
    private int resizeY;
    private int resizeZ;
    private int xan2d;
    private int yan2d;
    private int zan2d;
    private int cost;
    private boolean isTradeable;
    private int stackable;
    private int inventoryModel;
    private boolean members;
    private short[] colorFind;
    private short[] colorReplace;
    private short[] textureFind;
    private short[] textureReplace;
    private int zoom2d;
    private int xOffset2d;
    private int yOffset2d;
    private int ambient;
    private int contrast;
    private int[] countCo;
    private int[] countObj;
    private String[] options;
    private String[] interfaceOptions;
    private int maleModel0;
    private int maleModel1;
    private int maleModel2;
    private int maleOffset;
    private int maleHeadModel;
    private int maleHeadModel2;
    private int femaleModel0;
    private int femaleModel1;
    private int femaleModel2;
    private int femaleOffset;
    private int femaleHeadModel;
    private int femaleHeadModel2;
    private int notedID;
    private int notedTemplate;
    private int team;
    private int shiftClickDropIndex;
    private int boughtId;
    private int boughtTemplateId;
    private int placeholderId;
    private int placeholderTemplateId;
    private Map<Integer, Object> params;

    public ItemDefinition(int id) {
        this.id = id;
        name = "null";
        resizeX = 128;
        resizeY = 128;
        resizeZ = 128;
        xan2d = 0;
        yan2d = 0;
        zan2d = 0;
        cost = 1;
        stackable = 0;
        members = false;
        zoom2d = 2000;
        xOffset2d = 0;
        yOffset2d = 0;
        options = new String[]{null, null, "Take", null, null};
        interfaceOptions = new String[]{null, null, null, null, "Drop"};
        maleModel0 = -1;
        maleModel1 = -1;
        maleModel2 = -1;
        maleHeadModel = -1;
        maleHeadModel2 = -1;
        femaleModel0 = -1;
        femaleModel1 = -1;
        femaleModel2 = -1;
        femaleHeadModel = -1;
        femaleHeadModel2 = -1;
        notedID = -1;
        notedTemplate = -1;
        shiftClickDropIndex = -2;
        boughtId = -1;
        boughtTemplateId = -1;
        placeholderId = -1;
        placeholderTemplateId = -1;
        params = new HashMap<>();
    }

    @Override
    public void decode(ByteBuffer buffer) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                inventoryModel = buffer.getShort() & 0xffff;
            } else if (opcode == 2) {
                name = ByteBufferExt.getString(buffer);
            } else if (opcode == 4) {
                zoom2d = buffer.getShort() & 0xffff;
            } else if (opcode == 5) {
                xan2d = buffer.getShort() & 0xffff;
            } else if (opcode == 6) {
                yan2d = buffer.getShort() & 0xffff;
            } else if (opcode == 7) {
                xOffset2d = buffer.getShort() & 0xffff;
                if (xOffset2d > Short.MAX_VALUE) {
                    xOffset2d -= 65536;
                }
            } else if (opcode == 8) {
                yOffset2d = buffer.getShort() & 0xffff;
                if (yOffset2d > Short.MAX_VALUE) {
                    yOffset2d -= 65536;
                }
            } else if (opcode == 11) {
                stackable = 1;
            } else if (opcode == 12) {
                cost = buffer.getInt();
            } else if (opcode == 16) {
                members = true;
            } else if (opcode == 23) {
                maleModel0 = buffer.getShort() & 0xffff;
                maleOffset = buffer.get() & 0xff;
            } else if (opcode == 24) {
                maleModel1 = buffer.getShort() & 0xffff;
            } else if (opcode == 25) {
                femaleModel0 = buffer.getShort() & 0xffff;
                femaleOffset = buffer.get() & 0xff;
            } else if (opcode == 26) {
                femaleModel1 = buffer.getShort() & 0xffff;
            } else if (opcode >= 30 && opcode < 35) {
                options[opcode - 30] = ByteBufferExt.getString(buffer);
                if (options[opcode - 30].equalsIgnoreCase("Hidden")) {
                    options[opcode - 30] = null;
                }
            } else if (opcode >= 35 && opcode < 40) {
                interfaceOptions[opcode - 35] = ByteBufferExt.getString(buffer);
            } else if (opcode == 40) {
                int var5 = buffer.get() & 0xff;
                colorFind = new short[var5];
                colorReplace = new short[var5];
                IntStream.range(0, var5).forEach(var4 -> {
                    colorFind[var4] = (short) (buffer.getShort() & 0xffff);
                    colorReplace[var4] = (short) (buffer.getShort() & 0xffff);
                });
            } else if (opcode == 41) {
                int var5 = buffer.get() & 0xff;
                textureFind = new short[var5];
                textureReplace = new short[var5];
                IntStream.range(0, var5).forEach(var4 -> {
                    textureFind[var4] = (short) (buffer.getShort() & 0xffff);
                    textureReplace[var4] = (short) (buffer.getShort() & 0xffff);
                });
            } else if (opcode == 42) {
                shiftClickDropIndex = buffer.get();
            } else if (opcode == 65) {
                isTradeable = true;
            } else if (opcode == 78) {
                maleModel2 = buffer.getShort() & 0xffff;
            } else if (opcode == 79) {
                femaleModel2 = buffer.getShort() & 0xffff;
            } else if (opcode == 90) {
                maleHeadModel = buffer.getShort() & 0xffff;
            } else if (opcode == 91) {
                femaleHeadModel = buffer.getShort() & 0xffff;
            } else if (opcode == 92) {
                maleHeadModel2 = buffer.getShort() & 0xffff;
            } else if (opcode == 93) {
                femaleHeadModel2 = buffer.getShort() & 0xffff;
            } else if (opcode == 95) {
                zan2d = buffer.getShort() & 0xffff;
            } else if (opcode == 97) {
                notedID = buffer.getShort() & 0xffff;
            } else if (opcode == 98) {
                notedTemplate = buffer.getShort() & 0xffff;
            } else if (opcode >= 100 && opcode < 110) {
                if (countObj == null) {
                    countObj = new int[10];
                    countCo = new int[10];
                }
                countObj[opcode - 100] = buffer.getShort() & 0xffff;
                countCo[opcode - 100] = buffer.getShort() & 0xffff;
            } else if (opcode == 110) {
                resizeX = buffer.getShort() & 0xffff;
            } else if (opcode == 111) {
                resizeY = buffer.getShort() & 0xffff;
            } else if (opcode == 112) {
                resizeZ = buffer.getShort() & 0xffff;
            } else if (opcode == 113) {
                ambient = buffer.get();
            } else if (opcode == 114) {
                contrast = buffer.get();
            } else if (opcode == 115) {
                team = buffer.get() & 0xff;
            } else if (opcode == 139) {
                boughtId = buffer.getShort() & 0xffff;
            } else if (opcode == 140) {
                boughtTemplateId = buffer.getShort() & 0xffff;
            } else if (opcode == 148) {
                placeholderId = buffer.getShort() & 0xffff;
            } else if (opcode == 149) {
                placeholderTemplateId = buffer.getShort() & 0xffff;
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
                    params.put(key, value);
                }
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
    }

}
