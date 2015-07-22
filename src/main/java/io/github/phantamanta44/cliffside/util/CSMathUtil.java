package io.github.phantamanta44.cliffside.util;

public final class CSMathUtil {
	
	public static final double PI_OVER_180 = Math.PI / 180.0;
	public static final double ONE_EIGHTY_OVER_PI = 180.0 / Math.PI;

	public static double lawOfCosine(double a, double b, double c) {
		return Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - c) / (2 * a * b));
	}
	
	public static double radToDeg(double radians) {
		return radians * ONE_EIGHTY_OVER_PI;
	}
	
	public static double degToRad(double degrees) {
		return degrees * PI_OVER_180;
	}
	
}
