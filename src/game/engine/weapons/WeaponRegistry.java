package game.engine.weapons;

public class WeaponRegistry {
	int code;
	int price;
	int damage;
	String name;
	int minRange;
	int maxRange;
	
	public WeaponRegistry(int code, int price){
		this.code=code;
		this.price=price;
	}
	
	public WeaponRegistry(int code, int price, int damage, String name){
		this(code, price);
		this.damage=damage;
		this.name=name;
	}
	
	public WeaponRegistry(int code, int price, int damage, String name, int minRange, int maxRange){
		this(code, price, damage, name);
		this.minRange=minRange;
		this.maxRange=maxRange;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinRange() {
		return minRange;
	}

	public void setMinRange(int minRange) {
		this.minRange = minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}
	
	
}
