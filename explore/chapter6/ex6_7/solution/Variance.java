package chapter6.ex6_7.solution;

import ks.java.func.Function;

public class Variance {

	public static Option<Double> computeVariance(List<Double> series) {
//		return series.isEmpty() 
//				? Option.none()
//				: Option.some(mean(varianceBeforeMean(series)));
		return varianceF.apply(series);
	}

	private static List<Double> varianceBeforeMean(List<Double> series) {
		return series.map(x -> Math.pow(x - mean(series), 2));
	}
	
	private static Double sum(List<Double> series) {
		return series.foldLeft(Double.valueOf(0.0), x -> y -> x + y);
	}

	private static Double mean(List<Double> series) {
		return sum(series) / series.length();
	}
	
	
	private static Function<List<Double>, Double> sumF = s -> s.foldLeft(Double.valueOf(0.0), x -> y -> x + y);
	
	private static Function<List<Double>, Option<Double>> meanF = s -> s.isEmpty()
			? Option.none()
			: Option.some( sumF.apply(s) / s.length() );
			
	private static Function<List<Double>, Option<Double>> varianceF = 
			series -> meanF.apply(series).
				flatMap(m -> meanF.apply( series.map(x -> Math.pow(x - m, 2))));
					
}
