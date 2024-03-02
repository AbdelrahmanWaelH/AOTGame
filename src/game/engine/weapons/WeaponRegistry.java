package game.engine.weapons;
import game.engine.weapons.*;

public class WeaponRegistry {
	private final int code;
	private int price;
	private int damage;
	private String name = "";
	private int minRange;
	private int maxRange;
	//private Weapon w;
	
	public WeaponRegistry(int code, int price){
		this.code=code;
		this.price=price;
		//this.w = new PiercingCannon(0);
	}
	
	public WeaponRegistry(int code, int price, int damage, String name){
		this(code, price);
		this.damage=damage;
		this.name=name;
//		switch (this.code){
//		case 1: this.w = new PiercingCannon(this.damage);
//		case 2: this.w = new SniperCannon(this.damage);
//		case 3: this.w = new VolleySpreadCannon(this.damage,this.minRange,this.maxRange);
//		case 4: this.w = new WallTrap(this.damage);
//		}
		
	}
	
	public WeaponRegistry(int code, int price, int damage, String name, int minRange, int maxRange){
		this(code, price, damage, name);
		this.minRange=minRange;
		this.maxRange=maxRange;
	}
	public String toString(){
		return this.code + " " + this.price + " "+ this.damage + " " + this.name + " " + this.minRange + " " + this.maxRange;
	}
	public int getCode() {
		return code;
	}

	public int getPrice() {
		return price;
	}

	public int getDamage() {
		return damage;
	}

	public String getName() {
		return name;
	}

	public int getMinRange() {
		return minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

}
