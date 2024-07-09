package model;

import java.util.List;

public class CordinateCalculator {
	
	private static int degreesInRotation;

	public static double calculateEasting(double direction, double distance) { 
		return -Math.cos((direction / (double) degreesInRotation) + (Math.PI/2));
	}
	
	public static double calculateNorthing(double direction, double distance) {
		return Math.sin((direction / (double) degreesInRotation) + (Math.PI/2));
	}
	
	public static double calculateTotalEasting(List<Measurment> measurments) {
		double totalEasting = 0;
		for(Measurment measurment : measurments) {
			totalEasting += calculateEasting((double) measurment.getDirection(), (double) measurment.getDistance());
		}
		
		return totalEasting;
	}
	
	public static double calculateTotalNorthing(List<Measurment> measurments) {
		double totalNorthing = 0;
		for(Measurment measurment : measurments) {
			totalNorthing += calculateNorthing((double) measurment.getDirection(), (double) measurment.getDistance());
		}
		
		return totalNorthing;
	}

	public static int getDegreesInRotation() {
		// TODO Auto-generated method stub
		return degreesInRotation;
	}

	public static void setDegreesInRotation(int degrees) {
		// TODO Auto-generated method stub
		degreesInRotation = degrees;
		
	}
}
