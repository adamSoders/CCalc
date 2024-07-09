package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Measurment {
    private IntegerProperty direction;
    private FloatProperty distance;
    private IntegerProperty id;

    public Measurment(int direction, double distance, int id) {
        this.direction = new SimpleIntegerProperty(direction);
        this.distance = new SimpleFloatProperty((float) distance);
        this.id = new SimpleIntegerProperty(id);
      
    }

    public int getDirection() {
        return direction.get();
    }

    public void setDirection(int direction) {
        this.direction.set(direction);
    }

    public IntegerProperty directionProperty() {
        return direction;
    }

    public float getDistance() {
        return distance.get();
    }

    public void setDistance(float distance) {
        this.distance.set(distance);
    }

    public FloatProperty distanceProperty() {
        return distance;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
        
    }

    public IntegerProperty idProperty() {
        return id;
    }

	public void decrementId() {
		// TODO Auto-generated method stub
		setId(getId() - 1);
	}

	public void incrementId() {
		// TODO Auto-generated method stub
		setId(getId() + 1);
		
	}
}