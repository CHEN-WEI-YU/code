//convex hull

import java.util.Arrays;
import java.util.Random;

public class Onion {

    static int count =0;
    
    public static int ConvexHullVertex(Point2D[] a) {
        
        GrahamScan graham = new GrahamScan(a);
        Stack<Integer> give = new Stack<>();
        for (Point2D p : graham.hull()){
           for(int i=0;i<a.length;i++){
               if(p.x()==a[i].x() && p.y()==a[i].y()){                 
                   give.push(i);
                   break;
               }
           }
        }
        int[] ans = new int[give.size()];
        int iii=0;
        while(!give.isEmpty()){
            ans[iii]=give.pop();
            iii++;
        }  
        for(int i = ans.length-1 ;i>0;i--){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.002);
            a[ans[i]].drawTo(a[ans[i-1]]);
            StdDraw.show(100);
            if(i==1){
                a[ans[0]].drawTo(a[ans[ans.length-1]]);
                StdDraw.show(100);
            }       
        }
        Arrays.sort(ans);
        if (a.length-ans.length<=2){
            count++;
            return count;
        }
        else{
            int large=0;
            int size = a.length-ans.length;            
            Point2D[] atarashi = new Point2D[size];
            for (int i = 0; i < a.length ; i++){
                for(int j = 0; j < ans.length ; j++){
                    if(i==ans[j]){
                        break;
                    }                  
                    if(j==ans.length-1){        
                        atarashi[large]= new Point2D(a[i].x(),a[i].y());
                        large++;
                    } 
                } 
            }
            count++;
            ConvexHullVertex(atarashi);           
        }   
        return count;
    }

    public static void main(String[] args) {
        
        In text = new In(args[0]);
        String num = text.readLine();
        int n = Integer.parseInt(num);
        Point2D[] data = new Point2D[n];
        Random da = new Random();
        for (int i = 0 ; i < n ;i++){
            data[i] = new Point2D((da.nextDouble()),da.nextDouble());
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(0.005);
            data[i].draw();
        }
        int a = ConvexHullVertex(data);     
        System.out.println(a);
    }
}

/*20
0.35 0.17
0.12 0.11
0.54 0.53
0.83 0.20
0.68 0.23
0.44 0.49
0.21 0.40
0.34 0.09
0.31 0.52
0.36 0.95
0.17 0.65
0.86 0.48
0.77 0.67
0.55 0.78
0.54 0.86
0.02 0.40
0.56 0.19
0.49 0.95
0.00 0.83
0.28 0.11*/

