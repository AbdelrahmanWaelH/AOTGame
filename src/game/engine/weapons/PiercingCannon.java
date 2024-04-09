package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.titans.Titan;

public class PiercingCannon extends Weapon
{
	public static final int WEAPON_CODE = 1;

	public PiercingCannon(int baseDamage)
	{
		super(baseDamage);
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int resourcesGained = 0;
		PriorityQueue<Titan> removedTitans = new PriorityQueue<>();
		for (int i = 0; i < 5 && !laneTitans.isEmpty(); i++){
			Titan t = laneTitans.remove();
			resourcesGained	+= t.takeDamage(this.getDamage());
			removedTitans.add(t);
		} //pop 5 titans if available, apply damage to them
		while (!removedTitans.isEmpty()){
			Titan t = removedTitans.remove();
			laneTitans.add(t);
		} //add popped titans back into the original priority queue, their order will be figured out automatically by the PQ ADT
		return resourcesGained;
	}

}
