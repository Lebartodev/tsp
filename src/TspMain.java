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
    double rk[] = new double[11];
    double Rn[]=new double[11];
    private List<Double> data = new ArrayList<>();
    public TspMain(){
        fillList();
        System.out.println("Mx = "+Mx(COUNT_N));
        System.out.println("S^2="+S2(COUNT_N,Mx(COUNT_N)));
        System.out.println("S^2 fix="+S2fix(COUNT_N,Mx(COUNT_N)));
        for(int i = 0;i<11;i++){
        Rn[i]=Rk(COUNT_N,i,Mx(COUNT_N));
        rk[i] = rk(COUNT_N,i,Mx(COUNT_N));
        System.out.println(rk(COUNT_N,i,Mx(COUNT_N)));
        }
        //Tkop(COUNT_N,Mx(COUNT_N));
        AR ar = new AR(rk);
        ar.calcAR1(new double[][]{
                {Rk(COUNT_N,1,Mx(COUNT_N)),1,Rk(COUNT_N,0,Mx(COUNT_N))},
                {Rk(COUNT_N,0,Mx(COUNT_N)),0,Rk(COUNT_N,1,Mx(COUNT_N))}
        });
        ar.calcAR2(new double[][]{
                {Rk(COUNT_N,1,Mx(COUNT_N)),Rk(COUNT_N,2,Mx(COUNT_N)),1,Rk(COUNT_N,0,Mx(COUNT_N))},
                {Rk(COUNT_N,0,Mx(COUNT_N)),Rk(COUNT_N,1,Mx(COUNT_N)),0,Rk(COUNT_N,1,Mx(COUNT_N))},
                {Rk(COUNT_N,1,Mx(COUNT_N)),Rk(COUNT_N,0,Mx(COUNT_N)),0,Rk(COUNT_N,2,Mx(COUNT_N))}

        });
        ar.calcAR3(new double[][]{
                {Rk(COUNT_N,1,Mx(COUNT_N)),Rk(COUNT_N,2,Mx(COUNT_N)),Rk(COUNT_N,3,Mx(COUNT_N)),1,Rk(COUNT_N,0,Mx(COUNT_N))},
                {Rk(COUNT_N,0,Mx(COUNT_N)),Rk(COUNT_N,1,Mx(COUNT_N)),Rk(COUNT_N,2,Mx(COUNT_N)),0,Rk(COUNT_N,1,Mx(COUNT_N))},
                {Rk(COUNT_N,1,Mx(COUNT_N)),Rk(COUNT_N,0,Mx(COUNT_N)),Rk(COUNT_N,1,Mx(COUNT_N)),0,Rk(COUNT_N,2,Mx(COUNT_N))},
                {Rk(COUNT_N,2,Mx(COUNT_N)),Rk(COUNT_N,1,Mx(COUNT_N)),Rk(COUNT_N,0,Mx(COUNT_N)),0,Rk(COUNT_N,3,Mx(COUNT_N))}

        });
        SS ss = new SS(Rn,rk);

        ss.calcSS0();
        ss.calcSS1();
        ss.calcSS2();
        ss.calcSS3();
        ARSS arss = new ARSS(Rn);
    }

    public void calcEps2(){
        double eps=0;
        double[] r = new double[11];
        r[0]=1;
        r[1]=-0.2639;
        r[2]=-0.4501;
        for(int i = 0;i<9;i++){
            if(i!=0&&i!=1&&i!=2)
                r[i]=r[i-1]*(-0.3028)+r[i-2]*(- 0.1473);
            //System.out.println(i+": "+r[i]);
            eps+=Math.pow(r[i]-rk(COUNT_N,i,Mx(COUNT_N)),2);
        }
        System.out.println(eps);
    }
    public void calcEps3(){
        double eps=0;
        double[] r = new double[11];
        r[0]=1;
        r[1]=-0.2639;
        r[2]=-0.4501;
        r[3]=-0.2838-0.1021-0.1295;
        for(int i = 0;i<9;i++){
            if(i!=0&&i!=1&&i!=2&&i!=3)
                r[i]=r[i-1]*(-0.2838)+r[i-2]*(-0.1021)+r[i-3]*(-0.1295);
            //System.out.println(i+": "+r[i]);
            eps+=Math.pow(r[i]-rk(COUNT_N,i,Mx(COUNT_N)),2);
        }
        System.out.println(eps);

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








}
