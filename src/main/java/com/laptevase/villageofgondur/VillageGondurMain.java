package com.laptevase.villageofgondur;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Map;


public class VillageGondurMain extends GameApplication {

	Entity player;
	GondurFactory factory = new GondurFactory();
	List<Entity> obstacles;
	
	

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(80 * 16);
		settings.setHeight(60 * 16);
	}
	
	
	
	@Override
	protected void initPhysics() {
	    FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(VillageTypes.PLAYER, VillageTypes.OBSTACLE) {

	        // order of types is the same as passed into the constructor
	        @Override
	        protected void onCollisionBegin(Entity player, Entity obstacle) {
	  

	        }
	        @Override
	        protected void onCollisionEnd(Entity player, Entity obstacle) {
	
	        }
	    });
	}
	

	@Override
	protected void initGame() {
		
	
		FXGL.getGameWorld().addEntityFactory(new GondurFactory());
		FXGL.setLevelFromMap("untitledj.tmx");
		obstacles = FXGL.getGameWorld().getEntitiesByType(VillageTypes.OBSTACLE);
		
		player = FXGL.entityBuilder()
		        .type(VillageTypes.PLAYER)
		        .at(50, 50)
		        //.bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
		        .viewWithBBox(new Rectangle(25, 25, Color.BLUE))
		        .with(new CollidableComponent(true))
		        .buildAndAttach();
		
		PhysicsComponent physics = new PhysicsComponent();
		physics.setBodyType(BodyType.DYNAMIC);



		}

	@Override
	protected void initInput() {
	    Input input = FXGL.getInput(); // get input service


	    
	    input.addAction(new UserAction("Move Right") {
	        @Override
	        protected void onAction() {
	      	
	        	player.translateX(5);
	        	for (int i = 0; i < obstacles.size(); i++) {
	        		  if (player.isColliding(obstacles.get(i))) {
	        			  player.translateX(-5);
	        		  }
	        		}
	        	

	      
	        }
	    }, KeyCode.D);
	    
	        	
	    input.addAction(new UserAction("Move Left") {
	        @Override
	        protected void onAction() {
	        	
	        	 player.translateX(-5);
		        	for (int i = 0; i < obstacles.size(); i++) {
		        		  if (player.isColliding(obstacles.get(i))) {
		        			  player.translateX(5);
		        		  }
		        	}
	        	 	
	        }
	    }, KeyCode.A);
	 
	    input.addAction(new UserAction("Move Up") {
	        @Override
	        protected void onAction() {
	        	
	            player.translateY(-5); // move up 5 pixels
	        	for (int i = 0; i < obstacles.size(); i++) {
	        		  if (player.isColliding(obstacles.get(i))) {
	        			  player.translateY(5);
	        		  }
	        	}
	           
	        }
	    }, KeyCode.W);
	 
	    input.addAction(new UserAction("Move Down") {
	        @Override
	        protected void onAction() {
	        	
	            player.translateY(5); // move down 5 pixels
	        	for (int i = 0; i < obstacles.size(); i++) {
	        		  if (player.isColliding(obstacles.get(i))) {
	        			  player.translateY(-5);
	        		  }
	        	}
	             
	        }
	    }, KeyCode.S);
	    
        
	}

	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}


