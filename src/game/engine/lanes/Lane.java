package game.engine.lanes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;

import game.engine.base.Wall;
import game.engine.interfaces.Attackee;
import game.engine.interfaces.Attacker;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;

public class Lane implements Comparable<Lane>, Attackee, Attacker
{
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
	
	void addTitan(Titan titan){
		titans.add(titan);
	}
	
	void addWeapon(Weapon weapon){
		weapons.add(weapon);
	}
	
	void moveLaneTitans(){
		Stack<Titan> tempQ= new Stack<>();
		while(titans.size()!=0){
			Titan currTitan;
			currTitan=titans.peek();
			if(!currTitan.hasReachedTarget()){
				currTitan.move();
				tempQ.add(currTitan);
				titans.remove();
			}
		}
		
		while(!tempQ.isEmpty()){
			addTitan(tempQ.pop());
		}
	}
	
	int performLaneTitansAttacks(){
		int resourcesGathered=0;
		Stack<Titan> tempQ= new Stack<>();
		while(titans.size()!=0){
			Titan currTitan;
			currTitan=titans.peek();
			if(currTitan.hasReachedTarget()){
				int currDamage=currTitan.getDamage();
				laneWall.takeDamage(currDamage);
				resourcesGathered+=laneWall.getResourcesValue();
				tempQ.add(currTitan);
				titans.remove();
			}
		}
		
		while(!tempQ.isEmpty()){
			addTitan(tempQ.pop());
		}
		
		return resourcesGathered;
	}
	
	int performLaneWeaponsAttacks(){
		int resourcesGathered=0;
		PriorityQueue<Titan> tempQ= new PriorityQueue<>();
		for(int i=0; i<weapons.size(); i++){
			Weapon currWeapon;
			currWeapon=weapons.get(i);
			int currDamage=currWeapon.getDamage();
			while(titans.size()!=0){  //uses weapon on each titan, so this iterates the titan queue
				Titan currTitan;
				currTitan=titans.peek();
				currTitan.takeDamage(currDamage);
				resourcesGathered+=currTitan.getResourcesValue();
				tempQ.add(currTitan);
				titans.remove();
				}
			while(tempQ.size()!=0){
				addTitan(tempQ.remove());
			}
		}
		
		return resourcesGathered;
		
	}
	
	boolean isLaneLost(){
		if(getLaneWall().getCurrentHealth()<=0){
			return true;
		}
		return false;
	}
	
	void updateLaneDangerLevel(){
		int dangerLevel=0;
		int titanCount=titans.size();
		int dangerSum=0;
		
		Iterator<Titan> value=titans.iterator();
		
		while(value.hasNext()){
			dangerSum+=value.next().getDangerLevel();
		}
		
		dangerLevel=dangerSum/titanCount;
		
		setDangerLevel(dangerLevel);
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getDamage'");
	}

	@Override
	public int getCurrentHealth() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getCurrentHealth'");
	}

	@Override
	public void setCurrentHealth(int health) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setCurrentHealth'");
	}

	@Override
	public int getResourcesValue() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getResourcesValue'");
	}
}
