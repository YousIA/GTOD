/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stclustering;

/**
 *
 * @author youcefd
 */
public class DistanceEuclidian extends DistanceFunction {
	
	static String NAME = "euclidian";
	

	public double calculateDistance(DoubleArray vector1, DoubleArray vector2) {
		double sum =0;	
		for(int i=0; i< vector1.data.length; i++){
			for (int j=0; j< vector2.data.length; j++){
			sum += Math.pow(vector1.data[i] - vector2.data[j], 2);
			 }
			}
		sum=sum/Math.pow((vector1.data.length*vector2.data.length),2);
		//System.out.println(sum);
		return Math.sqrt(sum);
	}

	@Override
	public String getName() {
		return NAME;
	}
	
}
