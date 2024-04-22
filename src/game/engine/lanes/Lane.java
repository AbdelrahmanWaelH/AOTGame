package game.engine.lanes;

import java.util.ArrayList;
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
	private int laneScore=0;

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
	
	public void addlaneScore(int scoreAdd){
		this.laneScore+=scoreAdd;
	}
	
	public int getLaneScore(){
		return this.laneScore;
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
		int resourcesGathered=0; //2 test cases passed if it's initially set 1, fails otherwise for some reason
		Stack<Titan> tempQ= new Stack<>();
		boolean allAttacked = false;

		while(!titans.isEmpty() && !allAttacked){
			Titan currTitan=titans.poll();
			tempQ.push(currTitan);
			if(currTitan.hasReachedTarget()){
				resourcesGathered+=currTitan.attack(laneWall);
			} else allAttacked = true; //coming titans haven't reached wall, they won't attack or be removed from the PQ
		}
		
		while(!tempQ.isEmpty()){
			addTitan(tempQ.pop());
		}
		return resourcesGathered;

	}
	//Redo 125
	public int performLaneWeaponsAttacks(){
		int totalResources = 0;
		Weapon currentWeapon = null;
		for(int i=0; i<this.weapons.size();i++){
			currentWeapon = this.weapons.get(i);
			totalResources += currentWeapon.turnAttack(this.titans);
		}
		return totalResources;
	}	
	
	public boolean isLaneLost(){
		return (getLaneWall().getCurrentHealth()<=0);
		
	}
	
	public void updateLaneDangerLevel(){
		int dangerSum=0;
		Stack<Titan> tempS= new Stack<>();
		Titan currTitan;
		
		while(titans.size()!=0){
			currTitan=titans.remove();
			dangerSum+=currTitan.getDangerLevel();
			tempS.push(currTitan);
		}
		
		while(tempS.size()!=0){
			titans.add(tempS.pop());
		}
		
		
		setDangerLevel(dangerSum);
	}

}
