package com.emveyh.ld25.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.emveyh.ld25.AudioManager;
import com.emveyh.ld25.GameState;
import com.emveyh.ld25.Globals;
import com.emveyh.ld25.MapCoord;
import com.emveyh.ld25.TextureManager;
import com.emveyh.ld25.map.MapManager;

public class PlayerEntity extends Entity {

	private String name;
	private boolean selectedable = true;

	public PlayerEntity(float x, float y, String name) {
		super(x, y, 50, 1, TextureManager.getInstance().getSprites()[0][0], TextureManager.getInstance().getSprites()[2][0]);
		this.name = name;
		this.speed = 200f;
	}

	@Override
	public void tickLogic() {

		if (this.getCurrentAnimationIndex() != 0 && EntityManager.getInstance().getSelectedPlayerEntity() != this) {
			this.changeAnimationTexture(0);
		}

		if (Gdx.input.justTouched() && EntityManager.getInstance().getSelectedPlayerEntity() == this) {
			// this.moveToPoint(Gdx.input.getX() *
			// (800f/Gdx.graphics.getWidth()), Gdx.graphics.getHeight() -
			// Gdx.input.getY() * (480f/Gdx.graphics.getHeight()), this.speed);
			// System.out.println(MapManager.getInstance().getMap()[0].length-1
			// - (int) ( Gdx.input.getY() * (480f/Gdx.graphics.getHeight() ))/
			// Globals.getInstance().getTileSize());
			int selectedX = (int) (Gdx.input.getX() * (800f / Gdx.graphics.getWidth())) / Globals.getInstance().getTileSize();
			int selectedY = MapManager.getInstance().getMap()[0].length - 1 - (int) (Gdx.input.getY() * (480f / Gdx.graphics.getHeight()))
					/ Globals.getInstance().getTileSize() + 2;
			
			if(selectedX != 0 && selectedY != MapManager.getInstance().getMap()[0].length-1 && selectedY != 0 && selectedX != MapManager.getInstance().getMap().length-1) {
				if (selectedX >= 0 && selectedX < MapManager.getInstance().getMap().length && selectedY >= 0
						&& selectedY < MapManager.getInstance().getMap()[0].length) {
			this.move(new MapCoord((int) (Gdx.input.getX() * (800f / Gdx.graphics.getWidth())) / Globals.getInstance().getTileSize(), MapManager.getInstance()
					.getMap()[0].length - 1 - (int) (Gdx.input.getY() * (480f / Gdx.graphics.getHeight())) / Globals.getInstance().getTileSize()+2));
				}
			}
		}

		if (canAttack) {
			Array<Entity> surroundingEntites = this.getSurroundingEntities(1, false);

			if (surroundingEntites.size > 0) {
				for (Entity entity : surroundingEntites) {
					if (entity instanceof BasicEnemy || entity instanceof Upgrade) {
						
						entity.onHit(this);
						break;
					}
				}
			}
		}

	}

	@Override
	public void onSelection() {
		EntityManager.getInstance().setSelectedPlayerEntity(this);
		this.changeAnimationTexture(1);
	}

	@Override
	public void onHit(Entity entity) {
		this.setCurrentHp(this.getCurrentHp()- entity.getDmg());
	}
	
	@Override
	public void onDeath() {
		if(EntityManager.getInstance().getSelectedPlayerEntity() == this) {
			EntityManager.getInstance().setSelectedPlayerEntity(null);
		}
		EntityManager.getInstance().unregisterEntity(this);
		
		AudioManager.getInstance().getPlayerDeath().play();
		if(EntityManager.getInstance().allPlayersDead()) {
			Globals.getInstance().changeGameState(GameState.GAME_OVER);
		}
	}
	
	public void heal(int amount) {
		
		if(this.getCurrentHp()+amount <= this.getHp()) {
			this.setCurrentHp(this.getCurrentHp()+amount);
		} else {
			this.setCurrentHp(this.getHp());
		}
		
	}
	
}
