/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stclustering;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author youcefd
 */
public class DBSCAN {
    protected List<Cluster> clusters = null;

		// For statistics
		protected long startTimestamp; // the start time of the latest execution
		protected long endTimestamp; 
		long numberOfNoisePoints; 
		
		//DistanceFunction distanceFunction = new DistanceEuclidian(); 
		
		KDTree kdtree;
		List<DoubleArray> bufferNeighboors1 = null;
		List<DoubleArray> bufferNeighboors2 = null;
		private List<String> attributeNames = null;
                
                
    public void AlgoDBSCAN() { 
			
		}
		
		public List<Cluster> runAlgorithm(String inputFile, int minPts, double epsilon, String separator) throws NumberFormatException, IOException {
			  
			startTimestamp =  System.currentTimeMillis();
			numberOfNoisePoints =0;
			List<DoubleArray> points = new ArrayList<DoubleArray>();

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			String line;

			attributeNames = new ArrayList<String>();
			 
			String currentInstanceName = null;

			while (((line = reader.readLine()) != null)) {

				if (line.isEmpty() == true ||
						line.charAt(0) == '#' || line.charAt(0) == '%') {
					continue;
				}
				

				String nameToUse = currentInstanceName == null ?  "Instance" + points.size() : currentInstanceName;
				currentInstanceName = null;
				String[] lineSplited = line.split(separator);
				double [] vector = new double[lineSplited.length];
				for (int i=0; i< lineSplited.length; i++) { 
					double value = Double.parseDouble(lineSplited[i]);
					vector[i] = value;
				}
				points.add(new DoubleArrayDBS(vector, nameToUse));
			}
			reader.close();
			if(attributeNames.size() == 0 && points.size() > 0){
				int dimensionCount = points.get(0).data.length;
				for(int i = 0; i < dimensionCount; i++){
					attributeNames.add("Attribute"+i);
				}
			}
			kdtree = new KDTree();
			kdtree.buildtree(points);
			clusters = new ArrayList<Cluster>();
			bufferNeighboors1 = new ArrayList<DoubleArray>();
			bufferNeighboors2 = new ArrayList<DoubleArray>();
			for(DoubleArray point : points) {
				DoubleArrayDBS pointDBS = (DoubleArrayDBS) point;
				if(pointDBS.visited == false) {
					pointDBS.visited = true;
					bufferNeighboors1.clear();
					kdtree.pointsWithinRadiusOf(pointDBS, epsilon, bufferNeighboors1);
					
					if(bufferNeighboors1.size() >= minPts -1) {
						expandCluster(pointDBS, bufferNeighboors1, epsilon, minPts);
					}
				}
			}
			
			for(DoubleArray point: points) {
				if(((DoubleArrayDBS)point).cluster == null){
					numberOfNoisePoints++;
				}
			}
			
		//	MemoryLogger.getInstance().checkMemory();

			endTimestamp =  System.currentTimeMillis();

			bufferNeighboors1 = null;
			bufferNeighboors2 = null;
			kdtree = null;

			return clusters;
		}

 private void expandCluster(DoubleArrayDBS currentPoint,	List<DoubleArray> neighboors, double epsilon, int minPts) {	
			
			Cluster cluster = new Cluster();
			clusters.add(cluster);
			
			cluster.addVector(currentPoint);
			currentPoint.cluster = cluster;
		
			for(int i = 0; i < neighboors.size(); i++) {
				DoubleArrayDBS newPointDBS = (DoubleArrayDBS) neighboors.get(i);
				
				
				if(newPointDBS.visited == false) {
						
					
					newPointDBS.visited = true;
					
					bufferNeighboors2.clear();
					kdtree.pointsWithinRadiusOf(newPointDBS, epsilon, bufferNeighboors2);
					
					
					if(bufferNeighboors2.size() >= minPts - 1) { 
						neighboors.addAll(bufferNeighboors2);
					}
				}
				
				if(newPointDBS.cluster == null){
					cluster.addVector(newPointDBS);
					newPointDBS.cluster = cluster;
				}
				
			}

			//MemoryLogger.getInstance().checkMemory();
		}

		/**
		 * Save the clusters to an output file
		 * @param output the output file path
		 * @throws IOException exception if there is some writing error.
		 */
		public void saveToFile(String output) throws IOException {
			BufferedWriter writer = new BufferedWriter(new FileWriter(output));
			
			
			for(String attributeName : attributeNames){
				writer.write("@ATTRIBUTEDEF=" + attributeName);
				writer.newLine();
			}
			
		
			for(int i=0; i< clusters.size(); i++){
				
				if(clusters.get(i).getVectors().size() >= 1){
					
					writer.write(clusters.get(i).toString());
					
					if(i < clusters.size()-1){
						writer.newLine();
					}
				}
			}
	
			writer.close();
		}
		
		public void printStatistics() {
			System.out.println("========== DBSCAN - SPMF 2.09 - STATS ============");
			System.out.println(" Total time ~: " + (endTimestamp - startTimestamp)
					+ " ms");
		//	System.out.println(" Max memory:" + MemoryLogger.getInstance().getMaxMemory() + " mb ");
			//System.out.println(" SSE (Sum of Squared Errors) (lower is better) : " + ClustersEvaluation.getSSE(clusters, distanceFunction));
			System.out.println(" Number of noise points: " + numberOfNoisePoints);
			System.out.println(" Number of clusters: " + clusters.size());
			System.out.println("=====================================");
		}
}
