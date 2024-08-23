package model;

import java.io.Serializable;

public class Measurement implements Serializable { // Contains information of a specific measurement
    private int direction;
    private double distance;
    private int id;
    private int ogDirection; // Used when degrees in rotation is updated (direction % degreesInRotation deletes useful information)

    public Measurement(int direction, double distance, int id) {
        this.direction = direction;
        this.distance = distance;
        this.id = id;
        this.ogDirection = direction;
      
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
    	this.direction = direction;
    }

    
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
    	this.distance = distance;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
    	this.id = id;
    }

	public void decrementId() {
		this.id--;
	}

	public void incrementId() {
		this.id++;
	}

	public int getOgDirection() {
		return ogDirection;
	}

}