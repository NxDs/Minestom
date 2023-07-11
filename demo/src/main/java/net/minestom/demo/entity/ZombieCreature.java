package net.minestom.demo.entity;

import net.minestom.server.attribute.Attribute;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.ai.EntityAIGroupBuilder;
import net.minestom.server.entity.ai.goal.FollowTargetGoal;
import net.minestom.server.entity.ai.target.ClosestEntityTarget;

import java.time.Duration;

public class ZombieCreature extends EntityCreature {
    public ZombieCreature() {
        super(EntityType.ZOMBIE);
        this.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.15f);

        addAIGroup(
                new EntityAIGroupBuilder()
                        .addTargetSelector(new ClosestEntityTarget(this, 500, Player.class))
                        .addGoalSelector(new FollowTargetGoal(this, Duration.ofMillis(500)))
                        .build()
        );
    }
}