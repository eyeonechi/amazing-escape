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
 * Traversal Strategy Factory
 * @author : Project Group 97
 */
public class TraversalStrategyFactory {

	private String type;

	public TraversalStrategyFactory(String type) {
		this.type = type;
	}
	
	public TraversalStrategy getTraversalStrategy(CarState carState, Integer wallSensitivity) {
		switch (type) {
		case "LeftHandRule":
			return new LeftHandRuleStrategy(wallSensitivity);
		case "AStar":
			return new AStarStrategy(carState);
		case "HumanDriver":
			return new HumanDriverStrategy();
		}
		return null;
	}

}
