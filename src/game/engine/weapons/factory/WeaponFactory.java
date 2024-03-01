package game.engine.weapons.factory;
import game.engine.weapons.WeaponRegistry;
import java.io.IOException;
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
