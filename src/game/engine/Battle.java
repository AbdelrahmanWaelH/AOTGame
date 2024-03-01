package game.engine;
import java.util.*;
import game.engine.titans.*;
import game.engine.lanes.Lane;
import game.engine.weapons.factory.WeaponFactory;
import game.engine.base.Wall;

public class Battle {
	private int[][] PHASES_APPROACHING_TITANS;
	final int WALL_BASE_HEALTH = 10000;
	private int numberOfTurns;
	private int resourcesGathered;
	private BattlePhase battlePhase;
	private int numberOfTitansPerTurn;
	private int score;
	private int titanSpawnDistance;
	private WeaponFactory weaponFactory; //when it's time to implement the weaponfactory class
	private HashMap<Integer, TitanRegistry> titansArchives;
	private ArrayList<Titan> approachingTitans;
	private PriorityQueue<Lane> lanes;
	private ArrayList<Lane> originalLanes;
	
	public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes, int initialResourcesPerLane ) throws Exception{ // check the throws IOException keyword
		this.numberOfTurns = numberOfTurns;
		this.score = score;
		this.titanSpawnDistance = titanSpawnDistance;
		this.battlePhase = BattlePhase.EARLY;
		//use initalNumOfLanes
		// use initialResourcesPerLane
		
	}
	private void initializeLanes(int numOfLanes){
		for (int i = 0; i < numOfLanes; i++){
			lanes.add(new Lane(new Wall(WALL_BASE_HEALTH)));
			originalLanes.add(new Lane(new Wall(WALL_BASE_HEALTH)));
		}
	}
	public int getNumberOfTurns() {
		return numberOfTurns;
	}

	public void setNumberOfTurns(int numberOfTurns) {
		this.numberOfTurns = numberOfTurns;
	}

	public BattlePhase getBattlePhase() {
		return battlePhase;
	}

	public void setBattlePhase(BattlePhase battlePhase) {
		this.battlePhase = battlePhase;
	}

	public int getNumberOfTitansPerTurn() {
		return numberOfTitansPerTurn;
	}

	public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn) {
		this.numberOfTitansPerTurn = numberOfTitansPerTurn;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTitanSpawnDistance() {
		return titanSpawnDistance;
	}

	public void setTitanSpawnDistance(int titanSpawnDistance) {
		this.titanSpawnDistance = titanSpawnDistance;
	}

	public int getResourcesGathered() {
		return resourcesGathered;
	}

	public void setResourcesGathered(int resourcesGathered) {
		this.resourcesGathered = resourcesGathered;
	}
}