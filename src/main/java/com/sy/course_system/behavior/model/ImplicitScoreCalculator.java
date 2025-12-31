package com.sy.course_system.behavior.model;

import com.sy.course_system.behavior.entity.LearningBehavior;
import com.sy.course_system.common.util.TimeDecayUtil;

public class ImplicitScoreCalculator {
    public static double calculate(LearningBehavior behavior) {
        // score = 行为权重 × log(1 + duration) × 时间衰减
        double behaviorWeight = BehaviorWeight.getWeight(behavior.getBehaviorType());

        int duration = behavior.getDuration() == null ? 0 : behavior.getDuration();

        double durationScore = Math.log(1 + duration);

        double timeDecay = TimeDecayUtil.decay(behavior.getCreateTime());

        return behaviorWeight * durationScore * timeDecay;

    }
}
