package game.engine.weapons;
import game.engine.interfaces.Attacker;

public abstract class Weapon implements Attacker {
	int baseDamage;
	
	public Weapon(int baseDamage){
		this.baseDamage= baseDamage;
	}
	public int getDamage(){
		return this.baseDamage;
	}


}
