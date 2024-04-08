package game.engine.interfaces;

public interface Attackee
{
	int getCurrentHealth();

	void setCurrentHealth(int health);

	int getResourcesValue();
	default boolean isDefeated(){
		if (this.getCurrentHealth() <= 0)
			return true;
		return false;
	}
	default int takeDamage(int damage){
		this.setCurrentHealth(damage);
		if (this.isDefeated())
		return this.getResourcesValue();
		return 0;
	}
}
