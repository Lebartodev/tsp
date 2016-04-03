import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Sasha on 09.03.2016.
 */
public class TspMain {
    private final String path = "data.txt";
    private final int COUNT_N=5000;
    private List<Double> data = new ArrayList<>();
    public TspMain(){
        fillList();
        //System.out.println("Mx = "+Mx(COUNT_N));
       // System.out.println("S^2="+S2(COUNT_N,Mx(COUNT_N)));
       // System.out.println("S^2 fix="+S2fix(COUNT_N,Mx(COUNT_N)));
       // for(int i = 0;i<11;i++){
        //    System.out.println("R("+i+") = "+Rk(COUNT_N,i,Mx(COUNT_N)));
        //    System.out.println("r("+i+") = "+rk(COUNT_N,i,Mx(COUNT_N)));
        //}
        //Tkop(COUNT_N,Mx(COUNT_N));
        calcBeta3(1);
        calcBeta2(1);
        calcBeta1(1);
    }
    public void fillList() {
        try {


            Scanner sc = new Scanner(new File(path));
            for (int i = 0; i < COUNT_N; i++) {
                //System.out.println(sc.nextLine());
                data.add(Double.valueOf(sc.nextLine()));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файла не существует");
        }
    }
    public double Mx(int n){
        double res=0;
        for(Double x:data){
            res+=x;
        }
        return res/n;



    }
    public double S2(int n,double mx){
        double res=0;
        for(Double x:data){
            res+=Math.pow((x-mx),2);

        }
        return res/n;
    }
    public double S2fix(int n,double mx){
        double res=0;
        for(Double x:data){
            res+=Math.pow((x-mx),2);

        }
        return res/(n-1);
    }
    public double Rk(int n,int k,double mx){
        double res=0;
        for(int i = 0;i<n-k;i++){
            res+=(data.get(i)-mx)*(data.get(i+k)-mx);
        }
        return res/(n-k-1);


    }
    public double rk(int n,int k,double mx){
        return Rk(n,k,mx)/Rk(n,0,mx);
    }
    public void Tkop(int n,double mx){
        double T;
        for(int m = 0;m<100;m++)
        if(Math.abs(rk(n,m,mx))<Math.pow(Math.E,-1)){
            System.out.println(m);

        }







    }

    public void calcBeta1(int M){
        double a[][]={
                {Rk(COUNT_N,1,Mx(COUNT_N)),1,Rk(COUNT_N,0,Mx(COUNT_N))},
                {Rk(COUNT_N,0,Mx(COUNT_N)),0,Rk(COUNT_N,1,Mx(COUNT_N))}
        };
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i][0] + "b + " + a[i][1] + "a = " + a[i][2]);
        }
        double x[]=new double[a.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = a[i][a[i].length - 1];
        }
        double m;
        for (int k = 1; k < a.length; k++) {
            for (int j = k; j < a.length; j++) {
                m = a[j][k - 1] / a[k - 1][k - 1];
                for (int i = 0; i < a[j].length; i++) {
                    a[j][i] = a[j][i] - m * a[k - 1][i];
                }
                x[j] = x[j] - m * x[k - 1];
            }
        }

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i][0] + "b + " + a[i][1] + "a = " + a[i][2]);
        }


        for (int i=a.length-1;i>=0;i--) {
            for (int j=i+1;j<a.length;j++) x[i]-=a[i][j]*x[j];
            x[i] = x[i] / a[i][i];
        }

        int t=(int) 'a';
        for (int i = 0; i < x.length; i++) {
            System.out.println(((char)t++)+" = "+x[i]);
        }
        System.out.println("a = "+Math.sqrt(x[x.length-1]));


    }
    public void calcBeta2(int M){
        double a[][]={
                {Rk(COUNT_N,1,Mx(COUNT_N)),Rk(COUNT_N,2,Mx(COUNT_N)),1,Rk(COUNT_N,0,Mx(COUNT_N))},
                {Rk(COUNT_N,0,Mx(COUNT_N)),Rk(COUNT_N,1,Mx(COUNT_N)),0,Rk(COUNT_N,1,Mx(COUNT_N))},
                {Rk(COUNT_N,1,Mx(COUNT_N)),Rk(COUNT_N,0,Mx(COUNT_N)),0,Rk(COUNT_N,2,Mx(COUNT_N))}
        };
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i][0] + "b1 + " + a[i][1] + "b2 + " + a[i][2]+"a = "+ a[i][3]);
        }
        double x[]=new double[a.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = a[i][a[i].length - 1];
        }
        double m;
        for (int k = 1; k < a.length; k++) {
            for (int j = k; j < a.length; j++) {
                m = a[j][k - 1] / a[k - 1][k - 1];
                for (int i = 0; i < a[j].length; i++) {
                    a[j][i] = a[j][i] - m * a[k - 1][i];
                }
                x[j] = x[j] - m * x[k - 1];
            }
        }

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i][0] + "b1 + " + a[i][1] + "b2 + " + a[i][2]+"a = "+ a[i][3]);
        }


        for (int i=a.length-1;i>=0;i--) {
            for (int j=i+1;j<a.length;j++) x[i]-=a[i][j]*x[j];
            x[i] = x[i] / a[i][i];
        }

        for (int i = 0; i < x.length; i++) {
            System.out.println("b("+i+") = "+x[i]);
        }
        System.out.println("a = "+Math.sqrt(x[x.length-1]));

    }
    public void calcBeta3(int M){
        double a[][]={
                {Rk(COUNT_N,1,Mx(COUNT_N)),Rk(COUNT_N,2,Mx(COUNT_N)),Rk(COUNT_N,3,Mx(COUNT_N)),1,Rk(COUNT_N,0,Mx(COUNT_N))},
                {Rk(COUNT_N,0,Mx(COUNT_N)),Rk(COUNT_N,1,Mx(COUNT_N)),Rk(COUNT_N,2,Mx(COUNT_N)),0,Rk(COUNT_N,1,Mx(COUNT_N))},
                {Rk(COUNT_N,1,Mx(COUNT_N)),Rk(COUNT_N,0,Mx(COUNT_N)),Rk(COUNT_N,1,Mx(COUNT_N)),0,Rk(COUNT_N,2,Mx(COUNT_N))},
                {Rk(COUNT_N,2,Mx(COUNT_N)),Rk(COUNT_N,1,Mx(COUNT_N)),Rk(COUNT_N,0,Mx(COUNT_N)),0,Rk(COUNT_N,3,Mx(COUNT_N))}
        };
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i][0] + "b1 + " + a[i][1] + "b2 + " + a[i][2]+"b3 + "+ a[i][3]+"a = "+a[i][4]);
        }
        double x[]=new double[a.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = a[i][a[i].length - 1];
        }
        double m;
        for (int k = 1; k < a.length; k++) {
            for (int j = k; j < a.length; j++) {
                m = a[j][k - 1] / a[k - 1][k - 1];
                for (int i = 0; i < a[j].length; i++) {
                    a[j][i] = a[j][i] - m * a[k - 1][i];
                }
                x[j] = x[j] - m * x[k - 1];
            }
        }

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i][0] + "b1 + " + a[i][1] + "b2 + " + a[i][2]+"b3 + "+ a[i][3]+"a = "+a[i][4]);
        }


        for (int i=a.length-1;i>=0;i--) {
            for (int j=i+1;j<a.length;j++) x[i]-=a[i][j]*x[j];
            x[i] = x[i] / a[i][i];
        }

        for (int i = 0; i < x.length; i++) {
            System.out.println("b("+i+") = "+x[i]);
        }
        System.out.println("a = "+Math.sqrt(x[x.length-1]));

    }




}
