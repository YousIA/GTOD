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
public class DoubleArrayDBS extends DoubleArrayInstance{
	
	boolean visited = false;
	Cluster cluster = null;

	/**
	 * Constructor
	 * @param data an array of double values
	 * @param String the name of this array
	 */
	public DoubleArrayDBS(double[] data, String name) {
		super(data, name);
	}

}

