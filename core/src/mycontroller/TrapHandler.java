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
 * Trap Handler Interface
 * @author : Project Group 97
 */
public interface TrapHandler {
	/**
	 * checkPass can inform the system about the reachability of the trap
	 * @param carState gives information of the car
	 * @param node store the reachability of the trap 
	 */
	public void checkPass();
}
