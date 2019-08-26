/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stclustering;
import java.util.*;
/**
 *
 * @author youcefd
 */
public class kmeans {

static int kmeans_results[]=new int[IO.m];
//static int var[]= new int[Format.nbr_ter+1];
int k=10;
Data centers[]=new Data[k];

 /**************************************************************************/
void init_kmeans()
{
    Random r=new Random();
for (int i=0;i<IO.m;i++)
{
kmeans_results[i]=r.nextInt(k);
}
}
/******************************************************************************/
void initialiser_centers()
{
for (int i=0;i<k;i++)
  {
      centers[i]=new Data();
      for (int j=0;j<IO.n;j++)
      {
          Point p=new Point();
          p.time=IO.database.get(i).ts.get(j).time;
          p.value=IO.database.get(i).ts.get(j).value;
	  centers[i].ts.add(p);
          centers[i].x=IO.database.get(i).x;
          centers[i].y=IO.database.get(i).y;
      }
  kmeans_results[i]=i;
  }
}
/******************************************************************************/
void assigned_clusters()
{
    Distances D=new Distances();
    
for (int i=0;i<IO.m;i++)
{
	float min=D.Euclidian(IO.database.get(i), centers[0]);
        int indice=0;
	for (int j=1;j<k;j++)
    {
	float d=D.Euclidian(IO.database.get(i), centers[j]);
		if (d<min){indice=j;}
    }
   
kmeans_results[i]=indice;
}
}
/*************************************************************************/
void FC(int cluster)
{
int cpt=0;
Point p=new Point();       

for (int j=0; j<IO.m;j++)
  {
   if (kmeans_results[j]==cluster)
    { 
        cpt++;
    }
   }

for (int i=0;i<IO.n;i++)
{
    float s=0;
    int t=0;
    double x=0;
    double y=0;
 for (int j=0; j<IO.m;j++)
  {
   if (kmeans_results[j]==cluster)
    { 
       x=x+IO.database.get(j).x;
       y=y+IO.database.get(j).y;
        s=s+IO.database.get(j).ts.get(i).value;
        t=t+IO.database.get(j).ts.get(i).time;
    }
   }
p.time=(int) ((float)t/cpt);
p.value=(float) s/cpt;
 centers[cluster].ts.add(p);
 centers[cluster].x=(double) x/cpt;
  centers[cluster].y=(double) y/cpt;
}
}
/********************************************************************/
void Kmeans_function()
{
int iteration=0;
initialiser_centers();
assigned_clusters();
while (iteration<2)
{
for (int i=0;i<k;i++)
{
FC(i);
}
assigned_clusters();
iteration++;
}
}
/*********************************************************************/
void Display_Kmeans_results()
{
//System.out.println("*************times series*************");


         for (int i = 0; i < k; i++) {
             System.out.println("Cluster "+i);
                      for (int j = 0; j < IO.m; j++) {

                          if (kmeans_results[j]==i)
                          {

			System.out.print(j +"  ");
                          }
                                                  }
                      System.out.println();

	}

}

}
