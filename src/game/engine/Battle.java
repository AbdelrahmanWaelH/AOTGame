package game.engine;
import java.util.*;
import game.engine.dataloader.DataLoader;
import game.engine.titans.*;
import game.engine.lanes.Lane;
import game.engine.weapons.factory.WeaponFactory;
import game.engine.base.Wall;

public class Battle {
	private static final int[][] PHASES_APPROACHING_TITANS;
	private static final int WALL_BASE_HEALTH = 10000;
	private int numberOfTurns;
	private int resourcesGathered;
	private BattlePhase battlePhase;
	private int numberOfTitansPerTurn;
	private int score;
	private int titanSpawnDistance;
	private final WeaponFactory weaponFactory; 
	private final HashMap<Integer, TitanRegistry> titansArchives;
	private final  ArrayList<Titan> approachingTitans;
	private final PriorityQueue<Lane> lanes;
	private final ArrayList<Lane> originalLanes; 
	
	static { //This block was taken from ChatGPT
        PHASES_APPROACHING_TITANS = new int[][] {
            { 1, 1, 1, 2, 1, 3, 4 },
            { 2, 2, 2, 1, 3, 3, 4 },
            { 4, 4, 4, 4, 4, 4, 4 }
        };
    }
	
	public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes, int initialResourcesPerLane ) throws Exception{ // check the throws IOException keyword
		this.numberOfTurns = numberOfTurns;
		this.resourcesGathered = 0;
		this.score = score;
		this.titanSpawnDistance = titanSpawnDistance;
		this.weaponFactory = new WeaponFactory();
		this.battlePhase = BattlePhase.EARLY;
		this.titansArchives = DataLoader.readTitanRegistry() ;
		this.approachingTitans = new ArrayList<Titan>();
		this.lanes = new PriorityQueue<Lane>();
		this.originalLanes = new ArrayList<Lane>();
		this.initializeLanes(initialNumOfLanes);
		}
	private void initializeLanes(int numOfLanes){
		for (int i = 0; i < numOfLanes; i++){
			Wall w = new Wall(WALL_BASE_HEALTH);
			Lane l = new Lane(w);
			lanes.add(l);
			originalLanes.add(l);
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
	public static int[][] getPHASES_APPROACHING_TITANS() {
		return PHASES_APPROACHING_TITANS;
	}
	public static int getWallBaseHealth() {
		return WALL_BASE_HEALTH;
	}
	public WeaponFactory getWeaponFactory() {
		return weaponFactory;
	}
	public HashMap<Integer, TitanRegistry> getTitansArchives() {
		return titansArchives;
	}
	public ArrayList<Titan> getApproachingTitans() {
		return approachingTitans;
	}
	public PriorityQueue<Lane> getLanes() {
		return lanes;
	}
	public ArrayList<Lane> getOriginalLanes() {
		return originalLanes;
	}
}
