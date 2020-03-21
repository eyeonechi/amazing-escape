/**
 * SWEN30006 Software Modelling and Design
 * Semester 1 2017
 * Project Part C - Amazing Escape
 * 
 * Project Group 97
 * 716090    Wuang Shen
 * 686141    Ruifeng Luo
 * 736901    Ivan Ken Weng Chee
 */

package mycontroller;

import java.util.ArrayList;

import controller.CarController;
import mycontroller.CarState;
import utilities.Coordinate;
import world.Car;
import world.WorldSpatial;

/**
 * AI controller to navigate the car safely through to the exit
 * @author : Project Group 97
 */
public class MyAIController extends CarController{
	
	// How many minimum units the wall is away from the player.
	private int wallSensitivity = 2;
	
	// Current state of the car
	private CarState carState;
	
	// Keeps track of the previous state
	private CarState previousState;
	
	// Traversal strategy used
	private TraversalStrategy traversalStrategy;
	
	// Dead end algorithm applied
	// private DeadEndAlgorithm deadEndAlgorithm;
	
	// Trap handler invoked
	// private TrapHandler trapHandler;

	public MyAIController(Car car) {
		super(car);
		
		carState = new CarState();
		previousState = carState;
		
		TraversalStrategyFactory traversalStrategyFactory = new TraversalStrategyFactory("LeftHandRule");
		traversalStrategy = traversalStrategyFactory.getTraversalStrategy(carState, wallSensitivity);
		
		// DeadEndAlgorithmFactory deadEndAlgorithmFactory = new DeadEndAlgorithmFactory(carState);
		// deadEndAlgorithm = deadEndAlgorithmFactory.getDeadEndAlgorithm();
		
		// TrapHandlerFactory trapHandlerFactory = new TrapHandlerFactory(carState);
		// trapHandler = trapHandlerFactory.getTrapHandler();
	}
	
	@Override
	public void update(float delta) {
		
		// Updates the current car state
		carState.update(getView(), getOrientation(), getRawVelocity(), getVelocity(), getAngle(), getHealth(), new Coordinate(getPosition()));
		//carState.getMap().printMap();
		
		// Check against the previous state
		checkStateChange();

		// Obtain the best move and apply
		ArrayList<Movement> moves = (ArrayList<Movement>) traversalStrategy.getMovement(carState, delta);
		if (!moves.isEmpty()) {
			applyMovement(moves, delta);
			moves.clear();
		}
		
	}
	
	/**
	 * Checks whether the car's state has changed or not, stops turning if it
	 *  already has.
	 */
	private void checkStateChange() {
		if(previousState.getOrientation() != carState.getOrientation()){
			if(previousState.isTurningLeft()){
				carState.setTurningLeft(false);
			}
			if(previousState.isTurningRight()){
				carState.setTurningRight(false);
			}
			previousState = carState;
		}
	}
	
	/**
	 * Moves the car based on moves returned by traversal strategy
	 * @param move  : The move obtained from traversal strategy
	 * @param delta : Time
	 */
	private void applyMovement(ArrayList<Movement> moves, Float delta) {
		for (Movement move : moves) {
			switch (move.toString()) {
			case "Accelerate":
				applyForwardAcceleration();
				break;
			case "Reverse":
				applyReverseAcceleration();
				break;
			case "TurnLeft":
				applyLeftTurn(getOrientation(), delta);
				break;
			case "TurnRight":
				applyRightTurn(getOrientation(), delta);
				break;
			case "Brake":
				applyBrake();
				break;
			case "Neutral":
				break;
			}
		}
	}
	
	
	
	/**
	 * Turn the car counter clock wise (think of a compass going counter clock-wise)
	 * @param orientation : Orientation of the car
	 * @param delta       : Time
	 */
	private void applyLeftTurn(WorldSpatial.Direction orientation, float delta) {
		switch(orientation){
		case EAST:
			if(!getOrientation().equals(WorldSpatial.Direction.NORTH)){
				turnLeft(delta);
			}
			break;
		case NORTH:
			if(!getOrientation().equals(WorldSpatial.Direction.WEST)){
				turnLeft(delta);
			}
			break;
		case SOUTH:
			if(!getOrientation().equals(WorldSpatial.Direction.EAST)){
				turnLeft(delta);
			}
			break;
		case WEST:
			if(!getOrientation().equals(WorldSpatial.Direction.SOUTH)){
				turnLeft(delta);
			}
			break;
		default:
			break;
		
		}
		
	}
	
	/**
	 * Turn the car clock wise (think of a compass going clock-wise)
	 * @param orientation : Orientation of the car
	 * @param delta       : Time
	 */
	private void applyRightTurn(WorldSpatial.Direction orientation, float delta) {
		switch(orientation){
		case EAST:
			if(!getOrientation().equals(WorldSpatial.Direction.SOUTH)){
				turnRight(delta);
			}
			break;
		case NORTH:
			if(!getOrientation().equals(WorldSpatial.Direction.EAST)){
				turnRight(delta);
			}
			break;
		case SOUTH:
			if(!getOrientation().equals(WorldSpatial.Direction.WEST)){
				turnRight(delta);
			}
			break;
		case WEST:
			if(!getOrientation().equals(WorldSpatial.Direction.NORTH)){
				turnRight(delta);
			}
			break;
		default:
			break;
		
		}
		
	}

}
