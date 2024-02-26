
public class Wall {
	int baseHealth;
	int currentHealth;
	
	public Wall(int baseHealth){
		this.baseHealth=baseHealth;
		this.currentHealth=baseHealth;
	}

	public int getBaseHealth() {
		return baseHealth;
	}

	public void setBaseHealth(int baseHealth) {
		this.baseHealth = baseHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	
}
