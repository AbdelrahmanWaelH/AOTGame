package game.engine.exceptions;

public class InvalidLaneException extends GameActionException{
static final String MSG = "Action done on invalid lane";

public InvalidLaneException(){
	super(MSG);
}
public InvalidLaneException(String message){
	super(message);
}
}
