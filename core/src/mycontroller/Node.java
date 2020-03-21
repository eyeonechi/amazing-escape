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

import tiles.MapTile;
import utilities.Coordinate;

/**
 * Represents a cell in the map
 * @author : Project Group 97
 */
public class Node {

	Coordinate coordinate;
	MapTile tile;
	Boolean accessible;
	Boolean position;

	public Node(Coordinate c, MapTile t, Boolean a, Boolean p) {
		this.coordinate = c;
		this.tile = t;
		this.accessible = a;
		this.position = p;
	}
	
	@Override
	public String toString() {
		if (!position) {
			if (tile.getName() == "Empty") {
				return "[None] ";
			} else if (tile.getName() == "Road") {
				return "[Road] ";
			} else if (tile.getName() == "Trap") {
				return "[Trap] ";
			} else if (tile.getName() == "Utility") {
				return "[Util] ";
			} else if (tile.getName() == "Wall") {
				return "[Wall] ";
			}
		}
		return "[Car ] ";
	}

}
