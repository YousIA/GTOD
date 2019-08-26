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
public class KDNode {
    	DoubleArray values;  // contains a vector
	int d;  // a dimension
	KDNode above;  // node above
	KDNode below;  // node below
	
	/**
	 * Constructor
	 * @param doubleArray a vector
	 * @param d  a dimension
	 */
	public KDNode(DoubleArray doubleArray, int d){
		this.values = doubleArray;
		this.d = d;
	}
}
