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
import java.util.List;

import world.WorldSpatial;

/**
 * Left Hand Wall-Hugging strategy similar to that of AIController
 * @author : Project Group 97
 */
public class LeftHandRuleStrategy implements TraversalStrategy{
	
	private List<Movement> movements;
	private Integer wallSensitivity;
	
	public LeftHandRuleStrategy(Integer wallSensitivity) {
		this.wallSensitivity = wallSensitivity;
		this.movements = new ArrayList<Movement>();
	}

	@Override
	public List<Movement> getMovement(CarState carState, Float delta) {
		// If you are not following a wall initially, find a wall to stick to!
		if (!carState.isFollowingWall()) {
			movements.add(new Accelerate());
			// Turn towards the north
			if (!carState.getOrientation().equals(WorldSpatial.Direction.NORTH)) {
				carState.setLastTurnDirection(WorldSpatial.RelativeDirection.LEFT);
				if (carState.getVelocity() > carState.getTurnSpeed()) {
					movements.add(new Reverse());
				}
				movements.add(new TurnLeft());
			}
			if (checkNorth(carState)){
				// Turn right until we go back to east!
				if(!carState.getOrientation().equals(WorldSpatial.Direction.EAST)) {
					carState.setLastTurnDirection(WorldSpatial.RelativeDirection.RIGHT);
					movements.add(new TurnRight());
					if (carState.getVelocity() > carState.getTurnSpeed()) {
						movements.add(new Reverse());
					}
				} else {
					carState.setFollowingWall(true);
				}
			}
		}
		// Once the car is already stuck to a wall, apply the following logic
		else {
			// Re-adjust the car if it is not aligned properly.
			if(carState.getLastTurnDirection() != null) {
				if(!carState.isTurningRight() && carState.getLastTurnDirection().equals(WorldSpatial.RelativeDirection.RIGHT)){
					movements.add(adjustRight(carState, delta));
				}
				else if(!carState.isTurningLeft() && carState.getLastTurnDirection().equals(WorldSpatial.RelativeDirection.LEFT)){
					movements.add(adjustLeft(carState, delta));
				}
			}
			
			if (carState.isTurningRight()){
				movements.add(new TurnRight());
				if (carState.getVelocity() > carState.getTurnSpeed()) {
					movements.add(new Reverse());
				}
				if (!checkWallAhead(carState)){
					carState.setLastTurnDirection(WorldSpatial.RelativeDirection.RIGHT);
					carState.setTurningRight(false);
				}
			}
			else if (carState.isTurningLeft()){
				// Apply the left turn if you are not currently near a wall.
				if (!checkFollowingWall(carState)) {
					movements.add(new TurnLeft());
					if (carState.getVelocity() > carState.getTurnSpeed()) {
						movements.add(new Reverse());
					}
				}
				else {
					carState.setTurningLeft(false);
				}
			}
			// Try to determine whether or not the car is next to a wall.
			if (checkFollowingWall(carState)) {
				// Maintain some velocity
				movements.add(new Accelerate());
				// If there is wall ahead, turn right!
				if (checkWallAhead(carState)){
					carState.setLastTurnDirection(WorldSpatial.RelativeDirection.RIGHT);
					carState.setTurningRight(true);
					
				} else {
					carState.setTurningRight(false);
					carState.setTurningLeft(false);
				}

			}
			// This indicates that I can do a left turn if I am not turning right
			else{
				carState.setLastTurnDirection(WorldSpatial.RelativeDirection.LEFT);
				carState.setTurningLeft(true);
			}
		}
		return movements;
	}

	/**
	 * Try to orient myself to a degree that I was supposed to be at if I am
	 * misaligned.
	 * @param carState : The state of the car
	 * @param delta    : Time
	 * @return         : Movement
	 */
	private Movement adjustLeft(CarState carState, float delta) {
		switch(carState.getOrientation()){
		case EAST:
			if((carState.getAngle() > WorldSpatial.EAST_DEGREE_MIN && carState.getAngle() < WorldSpatial.NORTH_DEGREE)){
				return new TurnRight();
			} else if ((carState.getAngle() > WorldSpatial.SOUTH_DEGREE && carState.getAngle() < WorldSpatial.EAST_DEGREE_MAX)) {
				return new TurnLeft();
			}
			break;
		case NORTH:
			if(carState.getAngle() > WorldSpatial.NORTH_DEGREE){
				return new TurnRight();
			} else if(carState.getAngle() < WorldSpatial.NORTH_DEGREE){
				return new TurnLeft();
			}
			break;
		case SOUTH:
			if(carState.getAngle() > WorldSpatial.SOUTH_DEGREE){
				return new TurnRight();
			}else if(carState.getAngle() < WorldSpatial.SOUTH_DEGREE){
				return new TurnLeft();
			}
			break;
		case WEST:
			if(carState.getAngle() > WorldSpatial.WEST_DEGREE){
				return new TurnRight();
			} else if(carState.getAngle() < WorldSpatial.WEST_DEGREE){
				return new TurnLeft();
			}
			break;
			
		default:
			break;
		}
		return new Neutral();
	}

	/**
	 * Try to orient myself to a degree that I was supposed to be at if I am misaligned.
	 * @param carState : The state of the car
	 * @param delta    : Time
	 * @return         : Movement
	 */
	private Movement adjustRight(CarState carState, float delta) {
		switch(carState.getOrientation()){
		case EAST:
			if(carState.getAngle() > WorldSpatial.SOUTH_DEGREE && carState.getAngle() < WorldSpatial.EAST_DEGREE_MAX){
				return new TurnLeft();
			} else if((carState.getAngle() > WorldSpatial.EAST_DEGREE_MIN && carState.getAngle() < WorldSpatial.NORTH_DEGREE)){
				return new TurnRight();
			}
			break;
		case NORTH:
			if(carState.getAngle() < WorldSpatial.NORTH_DEGREE){
				return new TurnLeft();
			} else if (carState.getAngle() > WorldSpatial.NORTH_DEGREE) {
				return new TurnRight();
			}
			break;
		case SOUTH:
			if(carState.getAngle() < WorldSpatial.SOUTH_DEGREE){
				return new TurnLeft();
			} else if(carState.getAngle() > WorldSpatial.SOUTH_DEGREE){
				return new TurnRight();
			}
			break;
		case WEST:
			if(carState.getAngle() < WorldSpatial.WEST_DEGREE){
				return new TurnLeft();
			} else if(carState.getAngle() > WorldSpatial.WEST_DEGREE){
				return new TurnRight();
			}
			break;
			
		default:
			break;
		}
		return new Neutral();
	}
	
	/**
	 * Check if you have a wall in front of you!
	 * @param carState : The state of the car
	 * @return         : Whether there is a wall ahead
	 */
	private boolean checkWallAhead(CarState carState) {
		switch(carState.getOrientation()) {
		case EAST:
			return checkEast(carState);
		case NORTH:
			return checkNorth(carState);
		case SOUTH:
			return checkSouth(carState);
		case WEST:
			return checkWest(carState);
		default:
			return false;
		}
	}
	
	/**
	 * Check if the wall is on your left hand side given your orientation
	 * @param carState : The state of the car
	 * @return         : Whether there is a wall
	 */
	private boolean checkFollowingWall(CarState carState) {
		switch(carState.getOrientation()) {
		case EAST:
			return checkNorth(carState);
		case NORTH:
			return checkWest(carState);
		case SOUTH:
			return checkEast(carState);
		case WEST:
			return checkSouth(carState);
		default:
			return false;
		}
	}
	
	/**
	 * Method below just iterates through the list and check in the correct coordinates.
	 * i.e. Given your current position is 10,10
	 * checkEast will check up to wallSensitivity amount of tiles to the right.
	 * checkWest will check up to wallSensitivity amount of tiles to the left.
	 * checkNorth will check up to wallSensitivity amount of tiles to the top.
	 * checkSouth will check up to wallSensitivity amount of tiles below.
	 */
	public boolean checkEast(CarState carState) {
		// Check tiles to my right
		for(int i = 0; i <= wallSensitivity; i++) {
			if (carState.getMap().getMap()[3][3 + i].tile.getName().equals("Wall")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkWest(CarState carState) {
		// Check tiles to my left
		for(int i = 0; i <= wallSensitivity; i++) {
			if (carState.getMap().getMap()[3][3 - i].tile.getName().equals("Wall")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkNorth(CarState carState) {
		// Check tiles to towards the top
		for(int i = 0; i <= wallSensitivity; i++) {
			if (carState.getMap().getMap()[3 - i][3].tile.getName().equals("Wall")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkSouth(CarState carState) {
		// Check tiles towards the bottom
		for(int i = 0; i <= wallSensitivity; i++) {
			if (carState.getMap().getMap()[3 + i][3].tile.getName().equals("Wall")) {
				return true;
			}
		}
		return false;
	}

}
