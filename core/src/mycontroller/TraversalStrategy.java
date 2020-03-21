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

import java.util.List;

/**
 * Traversal Strategy Interface
 * @author : Project Group 97
 */
public interface TraversalStrategy {

	/**
	 * Returns a list of moves for the car to execute
	 * @param carState : The state of the car
	 * @param delta    : Time
	 * @return         : List of movements
	 */
	public List<Movement> getMovement(CarState carState, Float delta);

}
