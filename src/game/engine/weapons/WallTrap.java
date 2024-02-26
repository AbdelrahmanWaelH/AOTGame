package game.engine.weapons;
import game.engine.titans.Titan;

public class WallTrap extends Weapon{
	Titan attackAction;
	
	public WallTrap(int baseDamage){
		super(baseDamage);
	}

	public Titan getAttackAction() {
		return attackAction;
	}

	public void setAttackAction(Titan attackAction) {
		this.attackAction = attackAction;
	}
	
	
}
