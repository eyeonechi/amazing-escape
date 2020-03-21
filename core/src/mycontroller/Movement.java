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

/**
 * Represents movement
 * @author : Project Group 97
 */
public abstract class Movement {

	final static String DESCRIPTION = "Movement";

	public Movement() {
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}

}
