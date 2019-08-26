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
public class STClustering {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IO inst1 =new IO();
       Distances inst2 =new Distances();                
        inst1.GenerateData();
        //inst1.MatrixDistances();
        //inst1.DisplayMatrixDistances();
        
        
        kmeans km=new kmeans();
        km.Kmeans_function();
        km.Display_Kmeans_results();
      //  inst1.DisplayData();
      /*  for (int i=0;i<inst1.database.size();i++)
        {
        for (int j=0;j<inst1.database.size();j++)
        {
          System.out.print(inst2.Euclidian(inst1.database.get(i), inst1.database.get(j))+ "  ");
        }
        System.out.println();
        }*/
        
       // System.out.print(inst2.Euclidian(inst1.database.get(2), inst1.database.get(2))+ "  ");
      
        
        
    }
    
}
