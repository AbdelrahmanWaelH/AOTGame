package game.engine.weapons.factory;
import java.io.File;
import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException; 

import game.engine.weapons.WeaponRegistry;

import java.util.*;

public class WeaponFactory {
	private HashMap<Integer, WeaponRegistry> weaponShop;
	File csvFile= new File("C:\\Users\\Omar Magdy\\OneDrive\\Documents\\GitHub\\AOTGame\\src");
	
	public WeaponFactory() throws IOException{ 
        try {
        	@SuppressWarnings("resource")
			BufferedReader currLine = new BufferedReader(new FileReader(csvFile));
            String line=currLine.readLine();
            while (line != null) {
                Object[] parts = line.split(",");
                if (parts.length >= 2) {
                    Integer code = (Integer) parts[0];
                    WeaponRegistry weapon = (WeaponRegistry) parts[1];
                    this.weaponShop.put(code, weapon);
                }
                line=currLine.readLine();
            }
        }finally{
        }
	}
	
	public HashMap<Integer, WeaponRegistry> getWeaponShop() {
		return weaponShop;
	}
}
