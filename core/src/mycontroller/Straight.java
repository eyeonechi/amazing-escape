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
 * Represents straight movement
 * @author : Project Group 97
 */
public abstract class Straight extends Movement {
	
	final static String DESCRIPTION = "Straight";

	public Straight() {
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}

}
