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
public class Distances {
 

float Euclidian (Data d1, Data d2)
{
    
    float distance=0;
    double diff;
    float v1, v2;
    
    
for (int i=0;i<d1.ts.size();i++)
{
    v1=d1.ts.get(i).value;
    v2=d2.ts.get(i).value;
    diff=(double)v1-v2;

    diff=Math.pow(diff,2);
    distance=distance+(float)diff;    
}
    
diff=(double)(d1.x-d2.x)+(d1.y-d2.y);
diff=Math.pow(diff,2);
distance=distance+(float)diff;
diff=(double) distance;
distance= (float)Math.sqrt(diff);
return distance;
}
/***********************************************************/
float DTW(Data d1, Data d2)
{
float distance=0;
ArrayList<Float> dtw=new ArrayList<Float>();

 for (int i=0; i<d1.ts.size();i++)
 {
 dtw.add((float)Double.POSITIVE_INFINITY);
 }
 
 //System.out.println(d1.ts.size()+ " "+d2.ts.size()+ "  "+d1.ts.size()*d2.ts.size()+ " "+dtw.size());
dtw.set(0, (float)0);

float cost=0;
for (int i=1; i<d1.ts.size();i++)
 {
     cost=(float)Math.abs(d1.ts.get(i).value-d2.ts.get(i).value);
     
     double v1=dtw.get((i-1));
     double v2=dtw.get(i);
     double v3=Math.min(v1, v2);
     cost=cost+(float)v3;
     dtw.set(i, cost);
  //   
 }

distance=dtw.get(d1.ts.size()-1);

double diff=(double)(d1.x-d2.x)+(d1.y-d2.y);
diff=Math.abs(diff);
distance=distance+(float)diff;
return distance;
}

/*****************************************************/
float STS(Data d1, Data d2)
{
float distance=0;

for (int i=1; i<d1.ts.size();i++)
 {
 distance=distance+(float) Math.abs(((d1.ts.get(i).value-d1.ts.get(i-1).value)/(d1.ts.get(i).time-d1.ts.get(i-1).time))-((d2.ts.get(i).value-d2.ts.get(i-1).value)/(d2.ts.get(i).time-d2.ts.get(i-1).time)));
 }


distance=(float)Math.sqrt((double)distance);

double diff=(double)(d1.x-d2.x)+(d1.y-d2.y);
diff=Math.abs(diff);
distance=distance+(float)diff;
return distance; 
}


/*****************************************************/
float Dismiss(Data d1, Data d2)
{
float distance=0;

for (int i=1; i<d1.ts.size();i++)
 {
 distance=distance+ ((float)Math.abs(d1.ts.get(i-1).value-d2.ts.get(i-1).value)+(float)Math.abs(d1.ts.get(i).value-d2.ts.get(i).value)*(d1.ts.get(i).time-d1.ts.get(i-1).time));
 }

double diff=(double)(d1.x-d2.x)+(d1.y-d2.y);
diff=Math.abs(diff);
distance=distance+(float)diff;
return distance; 
}
/******************************************************/
float PC(Data d1, Data d2)
{
float distance=0;
float mean1=0;
float mean2=0;
for (int i=0; i<d1.ts.size();i++)
 {
     mean1=mean1+d1.ts.get(i).value;
     mean2=mean2+d2.ts.get(i).value;
 }

mean1=(float)mean1/d1.ts.size();
mean2=(float)mean2/d2.ts.size();

float v1=0;
float v2=0;
float sum1=0;
float sum2=0;
float sum3=0;
for (int i=0; i<d1.ts.size();i++)
 {
v1=(float)Math.abs(d1.ts.get(i).value-mean1);
v2=(float)Math.abs(d2.ts.get(i).value-mean2);
sum1=sum1+v1*v2;
sum2=sum2+(float)Math.pow(v1, 2);
sum2=(float)Math.sqrt(sum2);
sum3=sum3+(float)Math.pow(v2, 2);
sum3=(float)Math.sqrt(sum3);
 }

distance=(float)sum1/(sum2*sum3);

double diff=(double)(d1.x-d2.x)+(d1.y-d2.y);
diff=Math.abs(diff);
distance=distance+(float)diff;
return distance;
}
}
