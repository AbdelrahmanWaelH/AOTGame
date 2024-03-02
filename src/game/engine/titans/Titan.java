package game.engine.titans;
import game.engine.interfaces.*;

public abstract class Titan implements Comparable <Titan>, Attacker, Attackee, Mobil{
	private final int baseHealth;
	private int currentHealth;
	private final int baseDamage;
	private final int heightInMeters;
	private int distanceFromBase;
	private int speed;
	private final int resourcesValue;
	private final int dangerLevel;
	
	
	public Titan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel){
		this.baseHealth = baseHealth;
		this.currentHealth = this.baseHealth;
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

	public void setCurrentHealth(int health) {
		if (health >= 0)
			this.currentHealth = health;
		else
		this.currentHealth = 0;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getResourcesValue() {
		return resourcesValue;
	}
	public int getDangerLevel(){
		return this.dangerLevel;
	}
	@Override
	public int getDistance() {
		return this.distanceFromBase;
	}
	
	@Override
	public int getDamage() {
		return this.baseDamage;
	}

	@Override
	public void setDistance(int distance) {
		this.distanceFromBase = distance;
		
	}
	public int getHeightInMeters(){
		return this.heightInMeters;
	}
	
}
	
