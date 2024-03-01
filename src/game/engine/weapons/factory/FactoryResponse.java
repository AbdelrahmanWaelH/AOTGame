package game.engine.weapons.factory;
import game.engine.weapons.Weapon;

public class FactoryResponse {
	private Weapon weapon;
	private int remainingResources;
	
	public FactoryResponse(Weapon weapon, int remainingResources){
		this.weapon=weapon;
		this.remainingResources=remainingResources;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public int getRemainingResources() {
		return remainingResources;
	}

	public void setRemainingResources(int remainingResources) {
		this.remainingResources = remainingResources;
	}
	
	
}
