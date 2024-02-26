
public class TitanRegistry {
	int code;
	int baseHealth;
	int baseDamage;
	int heightInMeters;
	int speed;
	int resourcesValue;
	int dangerLevel;
	
	public TitanRegistry(int code, int baseHealth, int baseDamage, int heightInMeters, int speed,
			int resourcesValue, int dangerLevel){
		this.code=code;
		this.baseHealth=baseHealth;
		this.baseDamage=baseDamage;
		this.heightInMeters=heightInMeters;
		this.speed=speed;
		this.resourcesValue=resourcesValue;
		this.dangerLevel=dangerLevel;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getBaseHealth() {
		return baseHealth;
	}

	public void setBaseHealth(int baseHealth) {
		this.baseHealth = baseHealth;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public int getHeightInMeters() {
		return heightInMeters;
	}

	public void setHeightInMeters(int heightInMeters) {
		this.heightInMeters = heightInMeters;
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

	public void setResourcesValue(int resourcesValue) {
		this.resourcesValue = resourcesValue;
	}

	public int getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(int dangerLevel) {
		this.dangerLevel = dangerLevel;
	}
	
	
}
