package game.engine.weapons.factory;
import java.io.*;
import game.engine.dataloader.*;
import game.engine.weapons.WeaponRegistry;
import java.util.*;
import java.io.IOException;
import game.engine.weapons.WeaponRegistry;
import java.util.HashMap;
import game.engine.dataloader.DataLoader;

public class WeaponFactory {
	private HashMap<Integer, WeaponRegistry> weaponShop;
	
	public WeaponFactory() throws IOException{ 
        this.weaponShop=DataLoader.readWeaponRegistry();
	}
	
	public HashMap<Integer, WeaponRegistry> getWeaponShop() {
		return weaponShop;
	}
}
