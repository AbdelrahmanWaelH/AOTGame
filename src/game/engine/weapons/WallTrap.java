package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.titans.Titan;

public class WallTrap extends Weapon
{
	public static final int WEAPON_CODE = 4;

	public WallTrap(int baseDamage)
	{
		super(baseDamage);
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int resourcesGained = 0;
		if (laneTitans.peek().hasReachedTarget()){
			resourcesGained = laneTitans.peek().takeDamage(this.getDamage());
			if (resourcesGained != 0)
				laneTitans.remove(); //remove titan from lane if defeated
		}
		return resourcesGained;
	}

}
