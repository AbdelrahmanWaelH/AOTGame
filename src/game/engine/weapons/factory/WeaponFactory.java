package game.engine.weapons.factory;
import java.io.*;

import game.engine.weapons.WeaponRegistry;

import java.util.*;

import game.engine.dataloader.*;

public class WeaponFactory {
	private HashMap<Integer, WeaponRegistry> weaponShop;
	File csvFile= new File("weapons.csv");
	
	public WeaponFactory() throws IOException{ 
     this.weaponShop = DataLoader.readWeaponRegistry();
	}
	
	public HashMap<Integer, WeaponRegistry> getWeaponShop() {
		return weaponShop;
	}
}
