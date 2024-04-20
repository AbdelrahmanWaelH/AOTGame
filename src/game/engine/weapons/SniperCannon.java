package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.titans.Titan;

public class SniperCannon extends Weapon
{
	public static final int WEAPON_CODE = 2;

	public SniperCannon(int baseDamage)
	{
		super(baseDamage);
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int res = 0;
		if (!laneTitans.isEmpty())
		res = laneTitans.peek().takeDamage(this.getDamage());
		if (res != 0)
		laneTitans.remove();
		return res; 
	}

}
