package game.engine.weapons.factory;

import java.io.IOException;

import java.util.HashMap;

import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.weapons.WeaponRegistry;

public class WeaponFactory
{
	private final HashMap<Integer, WeaponRegistry> weaponShop;

	public WeaponFactory() throws IOException
	{
		super();
		weaponShop = DataLoader.readWeaponRegistry();
	}

	public HashMap<Integer, WeaponRegistry> getWeaponShop()
	{
		return weaponShop;
	}
	public FactoryResponse buyWeapon(int resources, int weaponCode) throws InsufficientResourcesException{
		WeaponRegistry reg = weaponShop.get(weaponCode);
		if(resources>= reg.getPrice()){
			return new FactoryResponse(reg.buildWeapon(),resources-reg.getPrice());
		}
		else
			throw new InsufficientResourcesException(resources);
	}
	public void addWeaponToShop(int code, int price){
		WeaponRegistry reg = new WeaponRegistry(code,price);
		weaponShop.put(code,reg);
	}
	public void addWeaponToShop(int code, int price, int damage, String name){
		WeaponRegistry reg = new WeaponRegistry(code,price,damage,name);
		weaponShop.put(code,reg);
	}
	public void addWeaponToShop(int code, int price, int damage, String name, int minRange,int maxRange){
		WeaponRegistry reg = new WeaponRegistry(code,price,damage,name,minRange,maxRange);
		weaponShop.put(code,reg);
	}

}
