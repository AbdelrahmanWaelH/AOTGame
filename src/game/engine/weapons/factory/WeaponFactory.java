package game.engine.weapons.factory;
import java.io.IOException;
import game.engine.weapons.WeaponRegistry;
import java.util.*;

public class WeaponFactory {
	private HashMap<Integer, WeaponRegistry> weaponShop;
	public WeaponFactory() throws IOException{ 
		//initialize hashmap to data from csv file
	}
	
	public HashMap<Integer, WeaponRegistry> getWeaponShop() {
		return weaponShop;
	}
}
