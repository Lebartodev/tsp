/**
 * Created by Sasha on 18.04.2016.
 */
public class AR {
    private final int COUNT_N=5000;
    private double[] NCF1 = new double[11];
    private double[] NCF2 = new double[11];
    private double[] NCF3 = new double[11];
    private double rk[];
    public AR(double[] rk){
        this.rk = rk;

    }
    public void calcAR1(double[][] a){
        double[] betas = new double[1];
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



        for (int i=a.length-1;i>=0;i--) {
            for (int j=i+1;j<a.length;j++) x[i]-=a[i][j]*x[j];
            x[i] = x[i] / a[i][i];
        }

            System.out.println(("b = "+x[0]));

        System.out.println("a = "+Math.sqrt(x[x.length-1]));
        calcEps1(new double[]{x[0]});

    }
    public void calcEps1(double[] betas){
        NCF1[0]= 1;
        NCF1[1]= rk[1];
        for(int i = 2;i<NCF1.length;i++){
            NCF1[i] = betas[0] * NCF1[i-1];
        }
        double eps=0;
        for(int i =1;i<NCF1.length;i++){
            eps += Math.pow(NCF1[i] - rk[i], 2);
        }
        System.out.println("eps = "+eps);

    }
    public void calcAR2(double[][] a){
        System.out.println("AR2");
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


        for (int i=a.length-1;i>=0;i--) {
            for (int j=i+1;j<a.length;j++) x[i]-=a[i][j]*x[j];
            x[i] = x[i] / a[i][i];
        }

        for (int i = 0; i < x.length-1; i++) {
            System.out.println("b("+i+") = "+x[i]);
        }

        System.out.println("a = "+Math.sqrt(x[x.length-1]));
        calcEps2(new double[]{x[0],x[1]});

    }
    public void calcEps2(double[] betas){
        NCF2[0]= 1;
        NCF2[1]= rk[1];
        NCF2[2]= rk[2];
        for(int i = 3;i<NCF2.length;i++){
            NCF2[i] = betas[0] * NCF2[i-1]+betas[1] * NCF2[i-2];
        }
        double eps=0;
        for(int i =1;i<NCF2.length;i++){
            eps += Math.pow(NCF2[i] - rk[i], 2);
        }
        System.out.println("eps = "+eps);

    }
    public void calcAR3(double [][]a){
        System.out.println("AR3");
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



        for (int i=a.length-1;i>=0;i--) {
            for (int j=i+1;j<a.length;j++) x[i]-=a[i][j]*x[j];
            x[i] = x[i] / a[i][i];
        }

        for (int i = 0; i < x.length-1; i++) {
            System.out.println("b("+i+") = "+x[i]);
        }
        System.out.println("a = "+Math.sqrt(x[x.length-1]));
        calcEps3(new double[]{x[0],x[1],x[2]});

    }
    public void calcEps3(double[] betas){
        NCF3[0]= 1;
        NCF3[1]= rk[1];
        NCF3[2]= rk[2];
        NCF3[3]= rk[3];
        for(int i = 4;i<NCF1.length;i++){
            NCF3[i] = betas[0] * NCF3[i-1]+betas[1] * NCF3[i-2]+betas[2] * NCF3[i-3];
        }
        double eps=0;
        for(int i =1;i<NCF3.length;i++){
            eps += Math.pow(NCF3[i] - rk[i], 2);
        }
        System.out.println("eps = "+eps);

    }
}
