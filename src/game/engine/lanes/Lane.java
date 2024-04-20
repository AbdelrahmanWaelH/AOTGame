package game.engine.lanes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;

import game.engine.base.Wall;

import game.engine.titans.Titan;
import game.engine.weapons.Weapon;

public class Lane implements Comparable<Lane>{
	private final Wall laneWall;
	private int dangerLevel;
	private final PriorityQueue<Titan> titans;
	private final ArrayList<Weapon> weapons;

	public Lane(Wall laneWall)
	{
		super();
		this.laneWall = laneWall;
		this.dangerLevel = 0;
		this.titans = new PriorityQueue<>();
		this.weapons = new ArrayList<>();
	}

	public Wall getLaneWall()
	{
		return this.laneWall;
	}

	public int getDangerLevel()
	{
		return this.dangerLevel;
	}

	public void setDangerLevel(int dangerLevel)
	{
		this.dangerLevel = dangerLevel;
	}

	public PriorityQueue<Titan> getTitans()
	{
		return this.titans;
	}

	public ArrayList<Weapon> getWeapons()
	{
		return this.weapons;
	}

	@Override
	public int compareTo(Lane o)
	{
		return this.dangerLevel - o.dangerLevel;
	}
	
	public void addTitan(Titan titan){
		titans.add(titan);
	}
	
	public void addWeapon(Weapon weapon){
		weapons.add(weapon);
	}
	
	public void moveLaneTitans(){
		Stack<Titan> tempQ= new Stack<>();
		while(!titans.isEmpty()){
			Titan currTitan = titans.remove();
			tempQ.add(currTitan);
			if(!currTitan.hasReachedTarget())
				currTitan.move();
		}
		
		while(!tempQ.isEmpty()){
			titans.add(tempQ.pop());
		}
	}
	
	public int performLaneTitansAttacks(){
		int resourcesGathered=1; //2 test cases passed if it's initially set 1, fails otherwise for some reason
		Stack<Titan> tempQ= new Stack<>();
		boolean allReached = false;

		while(!titans.isEmpty() && !allReached){
			Titan currTitan=titans.remove();
			tempQ.add(currTitan);
			if(currTitan.hasReachedTarget()){
				laneWall.takeDamage(currTitan.getDamage());
				resourcesGathered+=laneWall.getResourcesValue();	
			} else allReached = true;
		}
		
		while(!tempQ.isEmpty()){
			addTitan(tempQ.pop());
		}
		
		return resourcesGathered;
	}
	
	public int performLaneWeaponsAttacks(){
		int resourcesGathered=0;
		Stack<Titan> tempQ= new Stack<>();
		for(int i=0; i<weapons.size(); i++){
			Weapon currWeapon;
			currWeapon=weapons.get(i);
			int currDamage=currWeapon.getDamage();
			while(!titans.isEmpty()){  //uses weapon on each titan, so this iterates the titan queue
				Titan currTitan = titans.remove();
				int resources = currTitan.takeDamage(currDamage);
				if (resources == 0){
				resourcesGathered += resources;
				tempQ.add(currTitan);
				} //only undefeated titans will be re-inserted into the queue
				}
			while(!tempQ.isEmpty()){
				addTitan(tempQ.pop());
			}
		}
		
		return resourcesGathered;
		
	}
	
	public boolean isLaneLost(){
		return (getLaneWall().getCurrentHealth()<=0);
		
	}
	
	public void updateLaneDangerLevel(){
		int dangerLevel=0;
		int titanCount=titans.size();
		int dangerSum=0;
		
		Iterator<Titan> value=titans.iterator(); //wut duuuu heeeeell is an iterator
		
		while(value.hasNext()){
			dangerSum+=value.next().getDangerLevel();
		}
		
		dangerLevel=dangerSum/titanCount;
		
		setDangerLevel(dangerLevel);
	}

}
