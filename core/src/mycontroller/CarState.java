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

import com.badlogic.gdx.math.Vector2;

import tiles.MapTile;
import utilities.Coordinate;
import world.WorldSpatial;
import world.WorldSpatial.Direction;

/**
 * Represents the state of the car at a given instant
 * @author : Project Group 97
 */
public class CarState {

	// Safe speed for turning
	private final float TURN_SPEED = 3;
	// Offset used to differentiate between 0 and 360 degrees
	private int EAST_THRESHOLD = 3;
	// Shows the last turn direction the car takes.
	private WorldSpatial.RelativeDirection lastTurnDirection = null;
	// Orientation of the car
	private Direction orientation;
	// Gets what the car can see
	private Map map;
	// Raw velocity vector
	private Vector2 rawVelocity;
	// Velocity of the car
	private float velocity;
	// Angle the car is facing
	private float angle;
	// Health of the car
	private int health;
	// Car's position on the map
	private Coordinate position;
	
	// This is initialized when the car sticks to a wall.
	private Boolean followingWall;
	private Boolean turningLeft;
	private Boolean turningRight;
	
	CarState() {
		this.followingWall = false;
		this.turningLeft = false;
		this.turningRight = false;
	}

	public void update(HashMap<Coordinate, MapTile> view, Direction orientation, Vector2 rawVelocity, float velocity, float angle, int health, Coordinate position) {
		this.orientation = orientation;
		this.rawVelocity = rawVelocity;
		this.velocity = velocity;
		this.angle = angle;
		this.health = health;
		this.position = position;
		convertView(view);
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public float getAngle() {
		return angle;
	}
	
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public Vector2 getRawVelocity() {
		return rawVelocity;
	}
	
	public void setRawVelocity(Vector2 rawVelocity) {
		this.rawVelocity = rawVelocity;
	}
	
	public Direction getOrientation() {
		return orientation;
	}
	
	public void setOrientation(Direction orientation) {
		this.orientation = orientation;
	}
	
	public void convertView(HashMap<Coordinate, MapTile> view){
		map = new Map(view, position);
	}
	
	public CarState getState(){
		return this;
	}
	
	public Map getMap(){
		return this.map;
	}

	public Boolean isFollowingWall() {
		return followingWall;
	}

	public void setFollowingWall(Boolean followingWall) {
		this.followingWall = followingWall;
	}

	public Boolean isTurningLeft() {
		return turningLeft;
	}

	public void setTurningLeft(Boolean turningLeft) {
		this.turningLeft = turningLeft;
	}

	public Boolean isTurningRight() {
		return turningRight;
	}

	public void setTurningRight(Boolean turningRight) {
		this.turningRight = turningRight;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	public float getTurnSpeed() {
		return TURN_SPEED;
	}

	public WorldSpatial.RelativeDirection getLastTurnDirection() {
		return lastTurnDirection;
	}

	public void setLastTurnDirection(WorldSpatial.RelativeDirection lastTurnDirection) {
		this.lastTurnDirection = lastTurnDirection;
	}

	public int getEastThreshold() {
		return EAST_THRESHOLD;
	}

	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}

}
