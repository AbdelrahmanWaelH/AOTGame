package game.engine.interfaces;

public interface Mobil
{
	int getDistance();

	void setDistance(int distance);

	int getSpeed();

	void setSpeed(int speed);
	default boolean hasReachedTarget(){
		return (this.getDistance() == 0);
	}
	default boolean move(){
		this.setDistance(this.getSpeed());
		return (this.hasReachedTarget());
	}
}
