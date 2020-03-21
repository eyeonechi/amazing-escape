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
 * Dead End Algorithm Factory
 * @author : Project Group 97
 */
public class DeadEndAlgorithmFactory {
	
	private String type;

	public DeadEndAlgorithmFactory(String type) {
		this.type = type;
	}
	
	public DeadEndAlgorithm getDeadEndAlgorithm(CarState carState) {
		switch (type) {
		case "UTurn":
			return new UTurnAlgorithm(carState.getMap(), carState);
		case "ThreePointer":
			return new ThreePointerAlgorithm(carState.getMap(), carState);
		case "ReverseOut":
			return new ReverseOutAlgorithm(carState.getMap(), carState);
		default:
			return new UTurnAlgorithm(carState.getMap(), carState);
		}
	}

}
