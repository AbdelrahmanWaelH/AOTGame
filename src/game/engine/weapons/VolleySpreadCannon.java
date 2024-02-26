package game.engine.weapons;

public class VolleySpreadCannon extends Weapon {
	final int minRange;
	final int maxRange;
	
	
	public VolleySpreadCannon(int baseDamage, int minRange, int maxRange){
		super(baseDamage);
		this.minRange= minRange;
		this.maxRange= maxRange;
		
	}

}
