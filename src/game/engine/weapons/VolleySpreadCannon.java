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
		int totalResourcesGained = 0;

		while (!laneTitans.isEmpty()){
			Titan t = laneTitans.remove();
			boolean inRange = false;
			if (t.getDistance() >= minRange && t.getDistance()  <= maxRange){
				inRange = true; 
				totalResourcesGained += t.takeDamage(this.getDamage()); 
				}
				if (!t.isDefeated() || !inRange) 
					reservedTitans.add(t);
		}
		while (!reservedTitans.isEmpty()){
			laneTitans.add(reservedTitans.pop());
		} //return living titans into lane 
		return totalResourcesGained; 
	}

}
