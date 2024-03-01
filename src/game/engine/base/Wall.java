package game.engine.base;
import game.engine.interfaces.Attackee;

public class Wall implements Attackee{
	int baseHealth;
	int currentHealth;
	
	public Wall(int baseHealth){
		this.baseHealth=baseHealth;
		this.currentHealth=baseHealth;
	}

	public int getBaseHealth() {
		return baseHealth;
	}
	
	@Override
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	@Override
	public void setCurrentHealth(int health) {
		this.currentHealth = health;
	}

	@Override
	public int getResourcesValue() {
		// TODO Auto-generated method stub
		return -1;
	}
	
	
}
