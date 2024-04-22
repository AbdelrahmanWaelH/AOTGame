package game.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.ArrayList;
import game.engine.lanes.*;
import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.Weapon;
import game.engine.weapons.WeaponRegistry;
import game.engine.weapons.factory.FactoryResponse;
import game.engine.weapons.factory.WeaponFactory;

public class Battle
{
	private static final int[][] PHASES_APPROACHING_TITANS =
	{
		{ 1, 1, 1, 2, 1, 3, 4 },
		{ 2, 2, 2, 1, 3, 3, 4 },
		{ 4, 4, 4, 4, 4, 4, 4 } 
	}; // order of the types of titans (codes) during each phase
	private static final int WALL_BASE_HEALTH = 10000;

	private int numberOfTurns;
	private int resourcesGathered;
	private BattlePhase battlePhase;
	private int numberOfTitansPerTurn; // initially equals to 1
	private int score; // Number of Enemies Killed
	private int titanSpawnDistance;
	private final WeaponFactory weaponFactory;
	private final HashMap<Integer, TitanRegistry> titansArchives;
	private final ArrayList<Titan> approachingTitans; // treated as a Queue
	private final PriorityQueue<Lane> lanes;
	private final ArrayList<Lane> originalLanes;

	public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes,
			int initialResourcesPerLane) throws IOException
	{
		super();
		this.numberOfTurns = numberOfTurns;
		this.battlePhase = BattlePhase.EARLY;
		this.numberOfTitansPerTurn = 1;
		this.score = score;
		this.titanSpawnDistance = titanSpawnDistance;
		this.resourcesGathered = initialResourcesPerLane * initialNumOfLanes;
		this.weaponFactory = new WeaponFactory();
		this.titansArchives = DataLoader.readTitanRegistry();
		this.approachingTitans = new ArrayList<Titan>();
		this.lanes = new PriorityQueue<>();
		this.originalLanes = new ArrayList<>();
		this.initializeLanes(initialNumOfLanes);
	}

	public int getNumberOfTurns()
	{
		return numberOfTurns;
	}

	public void setNumberOfTurns(int numberOfTurns)
	{
		this.numberOfTurns = numberOfTurns;
	}

	public int getResourcesGathered()
	{
		return resourcesGathered;
	}

	public void setResourcesGathered(int resourcesGathered)
	{
		this.resourcesGathered = resourcesGathered;
	}

	public BattlePhase getBattlePhase()
	{
		return battlePhase;
	}

	public void setBattlePhase(BattlePhase battlePhase)
	{
		this.battlePhase = battlePhase;
	}

	public int getNumberOfTitansPerTurn()
	{
		return numberOfTitansPerTurn;
	}

	public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn)
	{
		this.numberOfTitansPerTurn = numberOfTitansPerTurn;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public int getTitanSpawnDistance()
	{
		return titanSpawnDistance;
	}

	public void setTitanSpawnDistance(int titanSpawnDistance)
	{
		this.titanSpawnDistance = titanSpawnDistance;
	}

	public WeaponFactory getWeaponFactory()
	{
		return weaponFactory;
	}

	public HashMap<Integer, TitanRegistry> getTitansArchives()
	{
		return titansArchives;
	}

	public ArrayList<Titan> getApproachingTitans()
	{
		return approachingTitans;
	}

	public PriorityQueue<Lane> getLanes()
	{
		return lanes;
	}

	public ArrayList<Lane> getOriginalLanes()
	{
		return originalLanes;
	}

	private void initializeLanes(int numOfLanes)
	{
		for (int i = 0; i < numOfLanes; i++)
		{
			Wall w = new Wall(WALL_BASE_HEALTH);
			Lane l = new Lane(w);

			this.getOriginalLanes().add(l);
			this.getLanes().add(l);
		}
	} 
	
	public void refillApproachingTitans() throws IOException{
		int phase = 0;
		if (this.battlePhase == BattlePhase.EARLY)
			phase = 0;
		if (this.battlePhase == BattlePhase.INTENSE)
			phase = 1;
		if (this.battlePhase == BattlePhase.GRUMBLING)
			phase = 2;
		HashMap<Integer, TitanRegistry> Titans = DataLoader.readTitanRegistry();
		for (int i = 0; i < 7; i++){
			int titanCode = PHASES_APPROACHING_TITANS[phase][i];
			//having a titan code, we should create a titan of the appropriate type and add it to approachingTitans ArrayList
			this.approachingTitans.add(Titans.get(titanCode).spawnTitan(this.titanSpawnDistance)); //the spawnTitan method is called on the TitanRegistry corresponding to the current titan code
		}
	}
	public void purchaseWeapon(int weaponCode, Lane lane) throws InsufficientResourcesException, InvalidLaneException, IOException{
		if (lane.isLaneLost())
			throw new InvalidLaneException(); //make sure that lane is in the lanes PQ

		WeaponFactory factory = new WeaponFactory();
		FactoryResponse response = factory.buyWeapon(resourcesGathered, weaponCode);
		response.getWeapon();
		Weapon w = response.getWeapon();
		lane.addWeapon(w);
		setResourcesGathered(response.getRemainingResources());
		// this.moveTitans();
		// this.performWeaponsAttacks();
		// this.performTitansAttacks();
		// this.addTurnTitansToLane();
		// this.finalizeTurns();
	} 
	public void passTurn(){

		//huh??!
		//move titans
		//weapons attack
		//titans attack
		//add from approachingTitans to lanes
		//finalize turn
		this.moveTitans();//move titans
		this.performWeaponsAttacks();//weapons attack
		this.performTitansAttacks();//titans attack
		this.addTurnTitansToLane();//add from approachingTitans to lanes
		this.updateLanesDangerLevels();
		this.finalizeTurns();//finalize turn

	}
	 private void addTurnTitansToLane(){
		Stack<Lane> tempS = new Stack<>();
		while (!lanes.isEmpty()){
			tempS.add(lanes.remove());}

		Lane lastLane = tempS.peek();
		for (int i = 0; i < numberOfTitansPerTurn; i++){
			lastLane.addTitan(approachingTitans.remove(i));
			}
			lastLane.updateLaneDangerLevel();
		while (!tempS.isEmpty()){
			lanes.add(tempS.pop());
		}
	}
	 
	 @SuppressWarnings("unused")
	private void moveTitans(){
		 Stack<Lane> tempS= new Stack<>();
		 Lane currLane;
		 while(!lanes.isEmpty()){
			 currLane=lanes.remove();
			 if (!currLane.isLaneLost())
			 	currLane.moveLaneTitans();
			 tempS.push(currLane);
		 }
		 
		 while(!tempS.isEmpty()){
			 lanes.add(tempS.pop());
		 }
	 }
	 
	private int performWeaponsAttacks(){
		ArrayList<Lane> tempS= new ArrayList<Lane>();
		 Lane currLane = null;
		 int resourcesGathered=0;
	
		 while(!lanes.isEmpty()){
			 currLane=lanes.poll();
			 if (!currLane.isLaneLost()){
				tempS.add(currLane);
				resourcesGathered+=currLane.performLaneWeaponsAttacks();
			 }
		 }
		 
		 for(int i=0;i<tempS.size();i++){
			//Lane l = tempS.get(i);
			//l.addlaneScore(resourcesGathered);
			lanes.add(tempS.get(i));

		 }
		 score += resourcesGathered;
		
		 this.resourcesGathered += resourcesGathered;
		 return resourcesGathered;
			 
	 }
	 
	 @SuppressWarnings("unused")
	private int performTitansAttacks(){
		 Stack<Lane> tempS= new Stack<>();
		 Lane currLane;
		 int damageSum=0;
		 while(!lanes.isEmpty()){
			 currLane=lanes.remove();
			 damageSum+=currLane.performLaneTitansAttacks();
			 if(!currLane.isLaneLost()){
				tempS.push(currLane);
				//damageSum+=currLane.getLaneWall().getResourcesValue();
			 }
		 }
		 
		 while(!tempS.isEmpty()){
			 lanes.add(tempS.pop());
		 }
		 
		 return damageSum;
	 }
	 
	 @SuppressWarnings("unused")
	private void updateLanesDangerLevels(){
		 Stack<Lane> tempS= new Stack<>();
		 Lane currLane;
		 while(lanes.size()!=0){
			 currLane=lanes.remove();
			 currLane.updateLaneDangerLevel();
			 tempS.push(currLane);
		 }
		 
		 while(tempS.size()!=0){
			 lanes.add(tempS.pop());
		 }
	 }
	 private void finalizeTurns(){
		numberOfTurns++;
		if(numberOfTurns<15)
			battlePhase= BattlePhase.EARLY;
		else if(numberOfTurns>=15 && numberOfTurns<30)
			battlePhase=BattlePhase.INTENSE;
		else if(numberOfTurns>=35 && numberOfTurns%5==0){ //first digit divisible by 5 greater than 30 is 35 so yh
			battlePhase=BattlePhase.GRUMBLING;
			numberOfTitansPerTurn= 2*numberOfTitansPerTurn;
		} else battlePhase = BattlePhase.GRUMBLING;
	 }
	 private void performTurn(){

		//this.purchaseWeapon(WALL_BASE_HEALTH, null);
		//or just use passturn :p
		this.moveTitans();
		this.performWeaponsAttacks();
		this.performTitansAttacks();
		this.addTurnTitansToLane();
		this.updateLanesDangerLevels();
		this.finalizeTurns();

	 }
	public boolean isGameOver(){
		return (lanes.isEmpty());
	 }
	 
}
