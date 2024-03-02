package game.engine.base;
import game.engine.interfaces.Attackee;

public class Wall implements Attackee{
	private final int baseHealth;
	private int currentHealth;
	
	public Wall(int baseHealth){
		this.baseHealth=baseHealth;
		this.currentHealth=baseHealth;
	}

	public int getBaseHealth() {
		return baseHealth;
	}

	@Override
	public int getCurrentHealth() {
		// TODO Auto-generated method stub
		return this.currentHealth;
	}

	@Override
	public void setCurrentHealth(int health) {
		if (health >= 0)
			this.currentHealth = health;
		else
		this.currentHealth = 0;
	}

	@Override
	public int getResourcesValue() {
		// TODO Auto-generated method stub
		return -1;
	}
	

	
	
}
