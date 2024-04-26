package game.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.Weapon;
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
	
	public void refillApproachingTitans(){
		int phase;
		switch (battlePhase) {
			case EARLY : phase = 0; break;
			case INTENSE : phase = 1; break;
			case GRUMBLING : phase = 2; break;
			default : phase = 0; break;
		}
		for (int i = 0; i < 7; i++){
			int titanCode = PHASES_APPROACHING_TITANS[phase][i];
			//having a titan code, we should create a titan of the appropriate type and add it to approachingTitans ArrayList
			this.approachingTitans.add(titansArchives.get(titanCode).spawnTitan(this.titanSpawnDistance)); //the spawnTitan method is called on the TitanRegistry corresponding to the current titan code
		}
	}
	public void purchaseWeapon(int weaponCode, Lane lane) throws InsufficientResourcesException, InvalidLaneException, IOException{
		if (lane.isLaneLost() || !originalLanes.contains(lane) || !lanes.contains(lane)) 
			throw new InvalidLaneException();
		FactoryResponse response = weaponFactory.buyWeapon(resourcesGathered, weaponCode);
		Weapon w = response.getWeapon();
		lane.addWeapon(w);
		setResourcesGathered(response.getRemainingResources());
        performTurn();
        
	} 
	public void passTurn(){
		this.performTurn();
	}
	private void addTurnTitansToLane(){
		Lane lastLane = lanes.peek();
		for (int i = 0; i<numberOfTitansPerTurn ; i++){
			if (approachingTitans.isEmpty()){
				refillApproachingTitans();
				lastLane.addTitan(approachingTitans.remove(0));
			}
			else
			    lastLane.addTitan(approachingTitans.remove(0));
		}	
	}
	
	private void moveTitans(){
		 Stack<Lane> tempLanes = new Stack<>();
		 Lane currLane;
		 while(!lanes.isEmpty()){
			currLane=lanes.remove();
			if (!currLane.isLaneLost()) //is this line necessary?
				currLane.moveLaneTitans();
			tempLanes.push(currLane);
		 }
		 
		 while(!tempLanes.isEmpty()){
			lanes.add(tempLanes.pop());
		 }
	 }
	 
	private int performWeaponsAttacks(){
		Stack<Lane> tempLanes = new Stack<>();
		Lane currLane = null;
		int resourcesGained = 0;
	
		while(!lanes.isEmpty()){
			 currLane=lanes.remove();
			 if (!currLane.isLaneLost()){
				tempLanes.add(currLane);
				resourcesGained+=currLane.performLaneWeaponsAttacks();
			 }
		}
		 
		for(int i=0;i<tempLanes.size();i++){
			lanes.add(tempLanes.get(i));
		}
		this.score += resourcesGained;
		this.resourcesGathered += resourcesGained;
		return resourcesGained;
	}
	 
	private int performTitansAttacks(){
		 Stack<Lane> tempS= new Stack<>();
		 Lane currLane;
		 int damageSum=0;
		 while(!lanes.isEmpty()){
			currLane=lanes.remove();
			damageSum+=currLane.performLaneTitansAttacks();
			if(!currLane.isLaneLost()){
				tempS.push(currLane);
			}
		}
		 
		while(!tempS.isEmpty()){
			lanes.add(tempS.pop());
		}
		 
		return damageSum;
	}
	 
	private void updateLanesDangerLevels(){
		Stack<Lane> tempStack = new Stack<>();
		Lane currLane;
		while (!lanes.isEmpty()) {
			currLane = lanes.remove();
			currLane.updateLaneDangerLevel();
			tempStack.push(currLane);
		}

		while (!tempStack.isEmpty()) {
			lanes.add(tempStack.pop());
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
		//this.purchaseWeapon(weaponCode, Lane); //No telling how this is gonna work, gonna leave it be since it passes all test cases
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
