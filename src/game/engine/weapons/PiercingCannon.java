package game.engine.weapons;

import java.util.PriorityQueue;
import java.util.Stack;

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
		int totalResourcesGained = 0;
		Stack<Titan> removedTitans = new Stack<>();
		for (int i = 0; i < 5 && !laneTitans.isEmpty(); i++){
			Titan t = laneTitans.remove();
			totalResourcesGained += t.takeDamage(this.getDamage());
			if (!t.isDefeated()){
			removedTitans.add(t);} //titan will be re-added if it is not defeated
		} //pop 5 titans if available, apply damage to them
		while (!removedTitans.isEmpty()){
			laneTitans.add(removedTitans.pop());
		} //add popped titans back into the original priority queue, their order will be figured out automatically by the PQ ADT
		return totalResourcesGained;
	}

}
