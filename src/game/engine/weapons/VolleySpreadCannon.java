package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.titans.Titan;

public class VolleySpreadCannon extends Weapon
{
	public static final int WEAPON_CODE = 3;

	private final int minRange;
	private final int maxRange;

	public VolleySpreadCannon(int baseDamage, int minRange, int maxRange)
	{
		super(baseDamage);
		this.minRange = minRange;
		this.maxRange = maxRange;
	}

	public int getMinRange()
	{
		return minRange;
	}

	public int getMaxRange()
	{
		return maxRange;
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		PriorityQueue<Titan> reservedTitans = new PriorityQueue<>(); 
		//stores titans who are either not attacked or not yet defeated
		int totalResourcesGained = 0;

		while (!laneTitans.isEmpty()){
			Titan t = laneTitans.remove();
			if (t.getDistance() >= minRange && t.getDistance()  <= maxRange){
			int resourcesGained = 0; 
			resourcesGained = t.takeDamage(this.getDamage());//titan gets attacked
				if (resourcesGained == 0){ //if titan is not defeated, add to PQ
					reservedTitans.add(t);}
			totalResourcesGained += resourcesGained; //add resources gained regardless
			}
		}
		while (!reservedTitans.isEmpty()){
			laneTitans.add(reservedTitans.remove());
		} //return living titans into lane 
		return totalResourcesGained; 
	}

}
