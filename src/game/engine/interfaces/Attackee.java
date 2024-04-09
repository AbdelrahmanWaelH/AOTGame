package game.engine.interfaces;

public interface Attackee
{
	int getCurrentHealth();

	void setCurrentHealth(int health);

	int getResourcesValue();

	default boolean isDefeated(){
		return (this.getCurrentHealth() <= 0);
	}

	default int takeDamage(int damage){
		this.setCurrentHealth(this.getCurrentHealth() - damage);
		if (this.isDefeated())
		return this.getResourcesValue();
		return 0;
	}
}
