package com.laptevase.villageofgondur;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GondurFactory implements EntityFactory {

	@Spawns("obstacle")
	public Entity newObstacle(SpawnData data) {
		return FXGL.entityBuilder()
				.from(data)
				.type(VillageTypes.OBSTACLE)
				.bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
				.with(new CollidableComponent(true))
				.build();
	}
	
		
	@Spawns("gem")
	public Entity newGem(SpawnData data) {
		return FXGL.entityBuilder()
				.from(data)
				.type(VillageTypes.GEM)
				.bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
				.with(new CollidableComponent(true))
				.build();
	}
	
	@Spawns("goldore")
	public Entity newGoldore(SpawnData data) {
		return FXGL.entityBuilder()
				.from(data)
				.type(VillageTypes.GOLDORE)
				.bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
				.with(new CollidableComponent(true))
				.build();
	}
	
}