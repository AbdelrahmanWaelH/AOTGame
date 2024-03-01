package game.engine.weapons.factory;
import java.io.*;
import game.engine.dataloader.*;
import game.engine.weapons.WeaponRegistry;
import java.util.*;

public class WeaponFactory {
	private HashMap<Integer, WeaponRegistry> weaponShop;
	File csvFile= new File("weapons.csv");
	
	public WeaponFactory() throws IOException{ 
        this.weaponShop=DataLoader.readWeaponRegistry();
	}
	
	public HashMap<Integer, WeaponRegistry> getWeaponShop() {
		return weaponShop;
	}
}
