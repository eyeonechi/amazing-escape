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
 * U Turn Algorithm
 * @author : Project Group 97
 */
public class UTurnAlgorithm implements DeadEndAlgorithm{
	
	private CarState carstate;
	private Map map;

	 UTurnAlgorithm(Map map, CarState carstate){
		 this.map=map;
		 this.carstate=carstate;
	}

	public List<Movement> getMovement(){
		return null;
	}

	public CarState getCarstate() {
		return carstate;
	}

	public void setCarstate(CarState carstate) {
		this.carstate = carstate;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}
