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
		WeaponRegistry y= weaponShop.get(weaponCode);
		if(resources>=y.getPrice()){
			return new FactoryResponse(y.buildWeapon(),resources-y.getPrice());
		}
		else
			throw new InsufficientResourcesException(resources);
		
	}
	public void addWeaponToShop(int code, int price){
		WeaponRegistry y= new WeaponRegistry(code,price);
		weaponShop.put(code,y);
	}
	public void addWeaponToShop(int code, int price, int damage, String name){
		WeaponRegistry y= new WeaponRegistry(code,price,damage,name);
		weaponShop.put(code,y);
	}
	public void addWeaponToShop(int code, int price, int damage, String name, int minRange,int maxRange){
		WeaponRegistry y= new WeaponRegistry(code,price,damage,name,minRange,maxRange);
		weaponShop.put(code,y);
	}

}
