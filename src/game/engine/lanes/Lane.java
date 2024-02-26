package game.engine.lanes;
import java.util.*;
import game.engine.base.Wall;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;



public class Lane implements Comparable<Lane>{
	Wall laneWall;
	int dangerLevel;
	PriorityQueue<Titan> titans;
	ArrayList<Weapon> weapons;
	
	public Lane(Wall laneWall){
		this.laneWall=laneWall;
	}
	
	public int compareTo(Lane o){
		if (this.dangerLevel>o.dangerLevel){
			return 1;
		}else if(this.dangerLevel<o.dangerLevel){
			return -1;
		}
		return 0;
	}

	public Wall getLaneWall() {
		return laneWall;
	}

	public void setLaneWall(Wall laneWall) {
		this.laneWall = laneWall;
	}

	public int getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(int dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public PriorityQueue<Titan> getTitans() {
		return titans;
	}

	public void setTitans(PriorityQueue<Titan> titans) {
		this.titans = titans;
	}

	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}

	public void setWeapons(ArrayList<Weapon> weapons) {
		this.weapons = weapons;
	}
	
	
}
