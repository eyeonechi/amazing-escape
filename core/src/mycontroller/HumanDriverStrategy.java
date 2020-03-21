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

/**
 * Experimental Strategy
 * @author Project Group 97
 */
public class HumanDriverStrategy implements TraversalStrategy {
	
	private List<Movement> movements;
	private Integer wallSensitivity = 3;
	
	public HumanDriverStrategy() {
		this.movements = new ArrayList<Movement>();
	}

	@Override
	public List<Movement> getMovement(CarState carState, Float delta) {
		movements.add(new Accelerate());
		if (checkNorth(carState)) {
			movements.add(new Reverse());
			movements.add(new TurnLeft());
		}
		return movements;
	}
	
	public Movement align(CarState carState) {
		switch (carState.getOrientation()) {
		case NORTH:
			switch (carState.getLastTurnDirection()) {
			case LEFT:
			case RIGHT:
			}
			break;
		case EAST:
			break;
		case SOUTH:
			break;
		case WEST:
			break;
		}
		return new Neutral();
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
