package model;

import java.util.List;

public class CordinateCalculator { // Calculates difference eastwards and northwards based on given measurements
	
	private static int degreesInRotation;

	public static double calculateEasting(double direction, double distance) { 
		return ((-Math.cos(((direction / (double) degreesInRotation) * Math.PI * 2) + (Math.PI/2))) * distance);
	}
	
	public static double calculateNorthing(double direction, double distance) {
		return ((Math.sin(((direction / (double) degreesInRotation) * Math.PI * 2) + (Math.PI/2))) * distance);
	}
	
	public static double calculateTotalEasting(List<Measurement> measurments) {
		double totalEasting = 0;
		for(Measurement measurment : measurments) {
			totalEasting += calculateEasting((double) measurment.getDirection(), (double) measurment.getDistance());
		}
		
		return totalEasting;
	}
	
	public static double calculateTotalNorthing(List<Measurement> measurments) {
		double totalNorthing = 0;
		for(Measurement measurment : measurments) {
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
	
	public static boolean degreesHasBeenSet() {
		return (degreesInRotation > 0);
	}
}
