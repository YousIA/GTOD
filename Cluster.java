/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stclustering;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author youcefd
 */
public class Cluster {
    protected List<DoubleArray> vectors = new ArrayList<DoubleArray>();

	public Cluster() {
		super();
	}

	public void addVector(DoubleArray vector) {
		vectors.add(vector);
	}

	
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		if(vectors.size() >=1){
			for(DoubleArray vector : vectors){
				buffer.append("[");
				buffer.append(vector.toString());
				buffer.append("]");
			}
		}
		return buffer.toString();
	}

	
	public List<DoubleArray> getVectors() {
		return vectors;
	}

	
	public void remove(DoubleArray vector) {
		vectors.remove(vector);		
	}
	
	public boolean contains(DoubleArray vector) {
		return vectors.contains(vector);
	}
}
