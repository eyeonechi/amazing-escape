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

import java.util.HashMap;

import tiles.MapTile;
import utilities.Coordinate;

/**
 * Map class used to represent the view surrounding the car
 * @author : Project Group 97
 */
public class Map {

	private Node[][] map = new Node[7][7];
	
	/**
	 * Builds a 2D array of nodes based on the current view
	 * @param v : The current view
	 * @param p : The position of the car
	 */
	public Map(HashMap<Coordinate, MapTile> v, Coordinate p) {
		Integer minY = Integer.MAX_VALUE;
		Integer minX = Integer.MAX_VALUE;
		for (Coordinate c : v.keySet()) {
			if (c.y < minY) {
				minY = c.y;
			}
			if (c.x < minX) {
				minX = c.x;
			}
		}
		for (Coordinate c : v.keySet()) {
			if (c.y == p.y && c.x == p.x) {
				map[7 - 1 - (c.y - minY)][c.x - minX] = new Node(c, v.get(c), true, true);
			} else {
				map[7 - 1 - (c.y - minY)][c.x - minX] = new Node(c, v.get(c), true, false);
			}
		}
	}

	/**
	 * Prints the map to System.out for debugging
	 */
	public void printMap() {
		System.out.println("Map:");
		for (int y = 0; y < 7; y ++) {
			for (int x = 0; x < 7; x ++) {
				System.out.print(map[y][x].toString());
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public Node[][] getMap() {
		return this.map;
	}
	
}
