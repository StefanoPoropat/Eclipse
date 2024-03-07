package br.com.henriquewilhelm.orbit;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;


/**
 * The Position class gathers the position of one element of the solar system  
 * (Sun, Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune, Pluto)
 * The calculations are:
 * <p>Position (rightAscention, declination, longitudeEcliptic and distance)</p>
 * 
 * @author Henrique Wilhelm v2.0.0
 * @author zoglmannk v1.0.0
 * @version v2.0.0
 */
public class Position implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1701865624853610383L;

	/**
	 * DR - Degrees to radians constant
	 */
	private static final double DR = Math.PI/180.0; //degrees to radians constant
	
	private double rightAscention;    //in radians
	private double declination;		 //in radians
	private double longitudeEcliptic; //in Degree
	private double latitudeEcliptic; //in Degree
	private double distance;
	
	// Getters and Setters
	public double getRightAscention() {
		return rightAscention;
	}
	public void setRightAscention(double rightAscention) {
		this.rightAscention = rightAscention;
	}
	public double getDeclination() {
		return declination;
	}
	public void setDeclination(double declination) {
		this.declination = declination;
	}
	public double getLongitudeEcliptic() {
		return longitudeEcliptic;
	}
	public void setLongitudeEcliptic(double longitudeEcliptic) {
		this.longitudeEcliptic = longitudeEcliptic;
	}
	public double getLatitudeEcliptic() {
		return latitudeEcliptic;
	}
	public void setLatitudeEcliptic(double latitudeEcliptic) {
		this.latitudeEcliptic = latitudeEcliptic;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	/**
	 * Contrutor with witht distance
	 * @param rightAscention double value
	 * @param declination double value
	 * @param longitudeEcliptic double value
	 * @param distance double value
	 */
	public Position(double rightAscention, double declination, double longitudeEcliptic, double distance) {
		this.rightAscention = rightAscention;
		this.declination = declination;
		this.longitudeEcliptic = longitudeEcliptic;
		this.distance = distance;
	}
	
	/**
	 * @return String value
	 */
	public String toString() {
		StringWriter sw = new StringWriter();
		PrintWriter writer = new PrintWriter(sw);
		
		writer.printf("Position (rightAscention %s, declination %s", 
				      new AstroHour(rightAscention/DR), 
				      new AstroDegrees(declination/DR));
		
		writer.flush();
		return sw.getBuffer().toString();
	}
			
	/**
	 * This class turns decimalDegrees in hour, minute and second ... 
	 * @author zoglmannk v1.0.0
	 *
	 */
	private static class AstroHour {
		/**
		 *  {@value #DEGRES_PER_HOUR}
		 */
		final static int DEGRES_PER_HOUR = 15;
		
		final int hours, minutes;
		final double seconds;
		
		/**
		 * Construtor of AstroHour
		 * @param  decimalDegrees double decimalDegrees in hour, minute and second ... 
		 * @author zoglmannk v1.0.0
		 *
		 */
		public AstroHour(double decimalDegrees) {
			double tmp = decimalDegrees/DEGRES_PER_HOUR;
			hours =  (int) tmp;
			
			tmp = (tmp - hours)*60;
			minutes = (int) tmp;
			
			seconds= (tmp - minutes)*60; 
		}
		/**
		 * @return String value
		 */
		public String toString() {
			StringWriter sw = new StringWriter();
			PrintWriter writer = new PrintWriter(sw);
			
			writer.printf("%dh %dm %(.2fs", hours, minutes, seconds);
			
			writer.flush();
			return sw.getBuffer().toString();
		}
		
	}
	/**
	 * This class turns decimalDegrees in degrees, hour, minute and second ... 
	 * @author zoglmannk v1.0.0
	 *
	 */
	private static class AstroDegrees {
		
		final int degrees, minutes;
		final double seconds;
		
		/**
		 * Coofnstrutor of AstroHour
		 * @param  decimalDegrees double decimalDegrees to degrees, minute and second ... 
		 * @author zoglmannk v1.0.0
		 *
		 */
		public AstroDegrees(double decimalDegrees) {
			double tmp = decimalDegrees;
			degrees =  (int) tmp;
			
			tmp = (tmp - degrees)*60;
			minutes = (int) tmp;
			
			seconds= (tmp - minutes)*60; 
		}
		/**
		 * @return String value
		 */
		public String toString() {
			StringWriter sw = new StringWriter();
			PrintWriter writer = new PrintWriter(sw);
			
			writer.printf("%ddegrees %dm %(.2fs)", degrees, minutes, seconds);
			
			writer.flush();
			return sw.getBuffer().toString();
		}
	}
}
