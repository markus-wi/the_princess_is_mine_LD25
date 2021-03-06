package com.emveyh.ld25.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.emveyh.ld25.AudioManager;
import com.emveyh.ld25.TextureManager;

public class HealOrb extends Upgrade {

	private int healAmount = 7;
	private float lifeTime = 0;
	private float deathTrigger = 10;
	
	public HealOrb(float x, float y) {
		super(x, y, 1, 1, TextureManager.getInstance().getSprites()[9][0]);
		this.setShowHealthBar(false);
	}

	
	
	@Override
	protected void tickLogic() {
		
		if(this.lifeTime < deathTrigger) {
			this.lifeTime += Gdx.graphics.getDeltaTime();
		} else {
			this.healAmount = 0;
			EntityManager.getInstance().unregisterEntity(this);
		}
		
	}



	@Override
	public void onHit(Entity entity) {

		if(entity instanceof PlayerEntity) {
			if(entity.getCurrentHp()+healAmount > entity.getHp()) {
				entity.setCurrentHp(entity.getHp());
			} else {
				entity.setCurrentHp(entity.getCurrentHp()+healAmount);
			}
			
			AudioManager.getInstance().getUpgrade().play();
			
			this.healAmount = 0;
			EntityManager.getInstance().unregisterEntity(this);
			
		}
		
	}

	
	
	

}
