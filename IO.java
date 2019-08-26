/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stclustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author youcefd
 */
public class IO {
 static ArrayList<ArrayList<Float>> matrix = new ArrayList<ArrayList<Float>>();
 static ArrayList<Data> database=new ArrayList<Data>(); 
       static  int m=1500;
       static int n=10;
/**************************************************************************/        
 void ReadFile()
      {
      
      }        
 /*************************************************************************/
 void GenerateData()
{

 Random r=new Random();
for (int i=0;i<m;i++)
 { Data d=new Data();
 for (int j=0;j<n;j++)
 {
   Point p=new Point();
   p.time=j+1;
   p.value=r.nextFloat();
 d.ts.add(p); 
 }
 d.x=r.nextDouble();
 d.y=r.nextDouble();
 database.add(d);
 
 }
}
/*************************************************************/
void DisplayData()
{
     Data d=new Data();
for (int i=0;i<m;i++)
 {
      d= database.get(i);
 for (int j=0;j<n;j++)
 {
   Point p=new Point();
  p=d.ts.get(j);
  System.out.print(p.time+ "  "+ p.value);
 }
 System.out.println("  ***"+ d.x+ "  "+ d.y);
 
 }
}
/*************************************************************/
void MatrixDistances()
{
    Distances dist=new Distances();
    for (int i=0;i<database.size();i++)
        {
            matrix.add(new ArrayList<Float>());
        for (int j=0;j<database.size();j++)
        {
          // matrix.get(i).add(dist.Euclidian(database.get(i),database.get(j)));
        //matrix.get(i).add(dist.DTW(database.get(i),database.get(j)));
      //matrix.get(i).add(dist.STS(database.get(i),database.get(j)));
      matrix.get(i).add(dist.Dismiss(database.get(i),database.get(j)));
         /*   if(i!=j)
            {
            matrix.get(i).add(dist.PC(database.get(i),database.get(j)));
            }
            else 
            {
            matrix.get(i).add((float)0);
            }*/
        }
        //System.out.println();
        }
}
/**********************************************************************/
void DisplayMatrixDistances()
{
  
    Distances dist=new Distances();
    for (int i=0;i<matrix.size();i++)
        {
          ArrayList row = new ArrayList<Float>();
          row=matrix.get(i);
        for (int j=0;j<row.size();j++)
        {
            float val=(float)row.get(j);
                System.out.print(val +"  ");
        }
        System.out.println();
        }

}
}
