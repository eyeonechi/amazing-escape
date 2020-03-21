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
 * Handles Grass Traps
 * @author : Project Group 97
 */
public class GrassTrapHandler implements TrapHandler {

	private Map map;
	private CarState carState;
	private Node node;
	private int trapX;
	private int trapY;
	
	public GrassTrapHandler(CarState carState, Node node) {
		this.carState = carState;
		this.node = node;
		this.map = carState.getMap();
	}
	
	@Override
	public void checkPass() {
		getTrapCoordinate();
		if(trapX == 3 && !map.getMap()[3][trapY].accessible){
			if(trapY > 3){
				for(int i = 5; i < 7;i++){
					if(map.getMap()[3][i].tile.getName().equalsIgnoreCase("Road")){
						map.getMap()[3][trapY].accessible = true;
					}
				}
			}else{
				for(int i = 0; i < 2;i++){
					if(map.getMap()[3][i].tile.getName().equalsIgnoreCase("Road")){
						map.getMap()[3][trapY].accessible = true;
					}
				}
			}
		}else if(trapY ==3 && !map.getMap()[trapX][3].accessible){
			if(trapX > 3){
				for(int i = 5; i < 7;i++){
					if(map.getMap()[3][i].tile.getName().equalsIgnoreCase("Road")){
						map.getMap()[3][trapY].accessible = true;
					}
				}
			}else{
				for(int i = 0; i < 2;i++){
					if(map.getMap()[3][i].tile.getName().equalsIgnoreCase("Road")){
						map.getMap()[3][trapY].accessible = true;
					}
				}	
			}
		}
		
	}
	
	/**
	 * Stores the trap's coordinate
	 */
	private void getTrapCoordinate(){
		for(int i = 0; i< 7; i++){
			for(int j = 0; j < 7;j++){
				if(node.coordinate.equals(map.getMap()[i][j])){
					trapX = i;
					trapY = j;
				}
			}
		}
	}

	public CarState getCarState() {
		return carState;
	}

	public void setCarState(CarState carState) {
		this.carState = carState;
	}
	
}
