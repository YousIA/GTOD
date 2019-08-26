/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stclustering;
import java.util.Arrays;
/**
 *
 * @author youcefd
 */
public class  DoubleArrayInstance extends DoubleArray{
	
	private String name = "";

	/** 
	 * Get the name of this time series
	 * @return a string (the name)
	 */
	public String getName() {
		return name;
	}

	/**
	 * Constructor
	 * @param values a list of double value
	 * @param name the name of this array
	 */
	public DoubleArrayInstance(double[] values, String name){
		super(values);
		this.name = name;
	}
		
	/**
	 * Obtain a string representation of this instance
	 * @return a String representation
	 */
	public String toString() {
		// We print the name of the instance followed by the data
		// that it contains (the double array)
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(" ");
		for(int i=0; i < this.data.length; i++){
			builder.append(this.data[i]);
			// if not the last one
			if(i != this.data.length-1){
				builder.append(" ");
			}
		}
		return  builder.toString();
	}
}

