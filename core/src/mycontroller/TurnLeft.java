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
 * Represents left turn
 * @author : Project Group 97
 */
public class TurnLeft extends Sideways {

	final static String DESCRIPTION = "TurnLeft";

	public TurnLeft() {
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}

}
