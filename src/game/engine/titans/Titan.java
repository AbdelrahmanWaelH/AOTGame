package game.engine.titans;

public abstract class Titan implements Comparable <Titan> {
	final int baseHealth;
	private int currentHealth;
	final int baseDamage;
	final int heightInMeters;
	private int distanceFromBase;
	private int speed;
	final int resourcesValue;
	final int dangerLevel;
	
	
	public Titan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel){
		this.baseHealth = baseHealth;
		this.currentHealth = baseHealth;
		this.baseDamage = baseDamage;
		this.heightInMeters = heightInMeters;
		this.distanceFromBase = distanceFromBase;
		this.speed = speed;
		this.resourcesValue = resourcesValue;
		this.dangerLevel = dangerLevel;
	}
	
	public int compareTo(Titan t){
		return this.distanceFromBase-t.distanceFromBase;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getDistanceFromBase() {
		return distanceFromBase;
	}

	public void setDistanceFromBase(int distanceFromBase) {
		this.distanceFromBase = distanceFromBase;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public int getResourcesValue() {
		return resourcesValue;
	}
	
	
}
	
