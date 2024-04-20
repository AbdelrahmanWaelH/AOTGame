package game.engine.weapons;

import java.util.PriorityQueue;
import java.util.Stack;

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
		Stack <Titan> reservedTitans = new Stack<>(); 
		//stores titans who are either not attacked or not yet defeated
		int totalResourcesGained = 0;

		while (!laneTitans.isEmpty()){
		Titan t = laneTitans.remove();
		int resourcesGained = 0; boolean inRange = false;
			if (t.getDistance() >= minRange && t.getDistance()  <= maxRange){
			inRange = true;
			resourcesGained = 0; 
			resourcesGained = t.takeDamage(this.getDamage());//titan gets attacked
			totalResourcesGained += resourcesGained; //add resources gained regardless
			}
			if (resourcesGained == 0 || !inRange) 
			reservedTitans.add(t);
		}
		while (!reservedTitans.isEmpty()){
			laneTitans.add(reservedTitans.pop());
		} //return living titans into lane 
		return totalResourcesGained; 
	}

}
