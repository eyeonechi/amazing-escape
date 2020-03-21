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
 * TrapHandlerFactory used to create TrapHandler objects
 * @author : Project Group 97
 */
public class TrapHandlerFactory {
	
	private String type;

	public TrapHandlerFactory(String type) {
		this.type = type;
	}
	
	/**
	 * getTrapHandler using factory to create a new sub TrapHandler, according to the type 
	 * @return sub TrapHandler
	 */
	public TrapHandler getTrapHandler(CarState carState, Node node) {
		switch (type) {
		case "Grass":
			return new GrassTrapHandler(carState, node);
		case "Lava":
			return new LavaTrapHandler(carState, node);
		case "Mud":
			return new MudTrapHandler(carState, node);
		}
		return null;
	}

}
