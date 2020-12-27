package com.osrsd.cache.def;

import com.osrsd.spring.domain.Definition;
import lombok.Data;

@Data
public final class SequenceDefinition implements Definition {

    private int id;
    private int[] frameIDs;
    private int[] chatFrameIds;
    private int[] frameLengths;
    private int[] frameSounds;
    private int frameStep;
    private int[] interleaveLeave;
    private boolean stretches;
    private int forcedPriority;
    private int leftHandItem;
    private int rightHandItem;
    private int maxLoops;
    private int precedenceAnimating;
    private int priority;
    private int replyMode;

    public SequenceDefinition(int id) {
        this.id = id;
        frameStep = -1;
        stretches = false;
        forcedPriority = 5;
        leftHandItem = -1;
        rightHandItem = -1;
        maxLoops = 99;
        precedenceAnimating = -1;
        priority = -1;
        replyMode = 2;
    }

}
