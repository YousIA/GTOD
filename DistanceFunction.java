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
public abstract class DistanceFunction {
 public abstract double calculateDistance(DoubleArray vector1, DoubleArray vector2);
	
	
	public  abstract String getName();
	
	
	public static DistanceFunction getDistanceFunctionByName(String name){
		/*if(DistanceCorrelation.NAME.equals(name)) {
			return new DistanceCorrelation();
		}
		else 
			if(DistanceCosine.NAME.equals(name)) {
			return new DistanceCosine();
		}
			else 
				if(DistanceEuclidian.NAME.equals(name)) {
			return new DistanceEuclidian();
		}
				else 
					if(DistanceManathan.NAME.equals(name)) {
			return 
					new DistanceManathan();
		}else 
			if(DistanceJaccard.NAME.equals(name)) {
			return 
					new DistanceJaccard();
		}
		return null;*/
	
			return new DistanceEuclidian();
		
	}   
}
