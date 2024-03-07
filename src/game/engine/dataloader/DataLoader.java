package game.engine.dataloader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import game.engine.titans.TitanRegistry;
import game.engine.weapons.WeaponRegistry;

public class DataLoader {
	private static final String TITANS_FILE_NAME= "titans.csv";
	private static final String WEAPONS_FILE_NAME = "weapons.csv";
	
	//DISCLAIMER: ChatGPT was used to aid in the use of the buffer reader and hashmap definition, the rest was learned from various Q&A's on programming forums 

		public static HashMap<Integer, TitanRegistry> readTitanRegistry() throws IOException{
		HashMap<Integer, TitanRegistry> outputHashMap = new HashMap<Integer, TitanRegistry>();
		try (BufferedReader reader = new BufferedReader(new FileReader(TITANS_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                outputHashMap.put(Integer.parseInt(data[0]), new TitanRegistry(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return outputHashMap;
	}
	
	public static HashMap<Integer, WeaponRegistry> readWeaponRegistry() throws IOException{
		HashMap<Integer, WeaponRegistry> outputHashMap = new HashMap<Integer, WeaponRegistry>();
		try (BufferedReader reader = new BufferedReader(new FileReader(WEAPONS_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); //cases length of string array calls appropriate constructors in dataloader
                if (data.length == 2){
                	outputHashMap.put(Integer.parseInt(data[0]), new WeaponRegistry(Integer.parseInt(data[0]),Integer.parseInt(data[1])));
                }
                else if (data.length == 4)
                {
                	outputHashMap.put(Integer.parseInt(data[0]), new WeaponRegistry(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),data[3]));
                }
                else 
                	outputHashMap.put(Integer.parseInt(data[0]), new WeaponRegistry(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),data[3],Integer.parseInt(data[4]),Integer.parseInt(data[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return outputHashMap;
	}

	
	public String getTITANS_FILE_NAME() {
		return TITANS_FILE_NAME;
	}
	public String getWEAPONS_FILE_NAME() {
		return WEAPONS_FILE_NAME;
	}
	
	
	// this was just used to test the static methods written above 
//		public static void main(String[] args) {
//	        try {
//	            HashMap<Integer, WeaponRegistry> weaponRegistryMap = readWeaponRegistry();
//	            printWeaponRegistry(weaponRegistryMap);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	    public static void printWeaponRegistry(HashMap<Integer, WeaponRegistry> weaponRegistryMap) {
//	        for (Map.Entry<Integer, WeaponRegistry> entry : weaponRegistryMap.entrySet()) {
//	            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//	        }
//	    }
	
}
