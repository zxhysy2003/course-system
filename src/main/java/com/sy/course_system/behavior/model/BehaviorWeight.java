package com.sy.course_system.behavior.model;

import com.sy.course_system.behavior.enums.LearnBehaviorType;

public class BehaviorWeight {
    public static double getWeight(LearnBehaviorType type) {
        return switch (type) {
            case VIEW -> 1.0;
            case STUDY -> 3.0;
            case FINISH -> 5.0;
            case FAVOURITE -> 4.0;
            default -> 0.0;
        };
    }
}
