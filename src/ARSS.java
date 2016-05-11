/**
 * Created by Sasha on 01.05.2016.
 */
public class ARSS {
    private double[] R;

    public ARSS(double[] r) {
        R = r;
        double[] alp11 = M1N1();
        System.out.println("M1N1");
        System.out.println("alpha0 = " + alp11[0]);
        System.out.println("alpha1 = " + alp11[1]);
        System.out.println("M1N3");
        double[] alp13 = M1N3();
        System.out.println("alpha0=" + alp13[0]);
        System.out.println("alpha1=" + alp13[1]);
        System.out.println("alpha2=" + alp13[2]);
        System.out.println("alpha3=" + alp13[3]);
        System.out.println("M2N1");
        double[] alp21 = M2N1();
        System.out.println("alpha0=" + alp21[0]);
        System.out.println("alpha1=" + alp21[1]);


        System.out.println("M2N2");
        double[] alp22 = M2N2();
        System.out.println("alpha0=" + alp22[0]);
        System.out.println("alpha1=" + alp22[1]);
        System.out.println("alpha2=" + alp22[2]);
        System.out.println("M2N3");
        double[] alp23 = M2N3();
        System.out.println("alpha0=" + alp23[0]);
        System.out.println("alpha1=" + alp23[1]);
        System.out.println("alpha2=" + alp23[2]);
        System.out.println("alpha3=" + alp23[3]);
        System.out.println("M3N1");
        double[] alp31 = M3N1();
        System.out.println("alpha0=" + alp31[0]);
        System.out.println("alpha1=" + alp31[1]);




        System.out.println("M3N2");
        double[] alp32 = M3N2();
        System.out.println("alpha0=" + alp32[0]);
        System.out.println("alpha1=" + alp32[1]);
        System.out.println("alpha2=" + alp32[2]);

        double rcsi[] = M3N2M2();
        System.out.println("M3N2 meth 2");
        for(int i = 0;i<10;i++){
            System.out.println(rcsi[i]);
        }




    }

    public double[] M1N1() {
        double[] alpha = new double[2];
        alpha[0] = Math.sqrt(R[0]);
        int i = 0;
        double b1 = R[2] / R[1];
        while (i < 1000) {
            alpha[1] = (R[1] - b1 * R[0]) / alpha[0];
            double tmp = R[0] - b1 * R[1] - Math.pow(alpha[1], 2) - alpha[0] * (alpha[1]) * b1;
            if (tmp < 0) return new double[2];
            else alpha[0] = Math.sqrt(tmp);
            i++;
        }

        return alpha;
    }

    public double[] M1N3() {
        double[] alpha = new double[4];
        alpha[0] = Math.sqrt(R[0]);
        int i = 0;
        double b1 = R[4] / R[3];
        while (i < 1000) {
            alpha[3] = (R[3] - b1 * R[2]) / alpha[0];
            alpha[2] = (R[2] - b1 * R[1] - alpha[3] * (b1 * alpha[0] + alpha[1])) / alpha[0];
            alpha[1] = (R[1] - b1 * R[0] - alpha[2] * (b1 * alpha[0] + alpha[1])
                    - alpha[3] * (b1 * (b1 * alpha[0] + alpha[1]) + alpha[2])) / alpha[0];
            double tmp = R[0] - b1 * R[1] - alpha[1] * (b1 * alpha[0] + alpha[1]) - alpha[2] * (b1 * (b1 * alpha[0] + alpha[1]) + alpha[2])
                    - alpha[3] * (b1 * (b1 * (b1 * alpha[0] + alpha[1]) + alpha[2]) + alpha[3]);
            if (tmp < 0) return new double[4];
            else alpha[0] = Math.sqrt(tmp);
            i++;
        }
        return alpha;
    }

    public double[] solveSystem(double[][] a) {

        double x[] = new double[a.length];
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


        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < a.length; j++) x[i] -= a[i][j] * x[j];
            x[i] = x[i] / a[i][i];
        }

        return x;
    }

    public double[] M2N1() {

        double[][] a = new double[][]{
                {R[1], R[0], R[2]},
                {R[2], R[1], R[3]}
        };
        double[] x = solveSystem(a);
        double[] alpha = new double[2];
        alpha[0] = Math.sqrt(R[0]);
        int i = 0;
        double b1 = x[0];
        double b2 = x[1];
        while (i < 100) {
            alpha[1] = (R[1] - b1 * R[0] - b2 * R[1]) / alpha[0];

            double tmp = R[0] - b1 * R[1] - b2 * R[2] - alpha[1] * (b1 * alpha[0] + alpha[1]);
            if (tmp < 0) return new double[]{0, 0};
            else alpha[0] = Math.sqrt(tmp);
            i++;
        }
        return alpha;

    }

    public double[] M2N2() {

        double[][] a = new double[][]{
                {R[2], R[1], R[3]},
                {R[3], R[2], R[4]}
        };
        double[] x = solveSystem(a);
        double[] alpha = new double[3];
        alpha[0] = Math.sqrt(R[0]);
        int i = 0;
        double b1 = x[0];
        double b2 = x[1];
        while (i < 100) {
            alpha[2] = (R[2] - b1 * R[1] - b2 * R[0]) / alpha[0];
            alpha[1] = (R[1] - b1 * R[0] - b2 * R[1] - alpha[2] * b1 * alpha[0]) / ((alpha[0] + alpha[2]));
            double tmp = R[0] - b1 * R[1] - b2 * R[2] - alpha[1] * (b1 * alpha[0] + alpha[1]) - alpha[2]
                    * (b1 * (b1 * alpha[0] + alpha[1]) + b2 * alpha[0] + alpha[2]);
            if (tmp < 0) return new double[3];
            else alpha[0] = Math.sqrt(tmp);
            i++;
        }
        return alpha;

    }

    public double[] M2N3() {

        double[][] a = new double[][]{
                {R[3], R[2], R[5]},
                {R[4], R[3], R[6]}
        };
        double[] x = solveSystem(a);
        double[] alpha = new double[4];
        alpha[0] = Math.sqrt(R[0]);
        int i = 0;
        double b1 = x[0];
        double b2 = x[1];
        while (i < 100) {
            alpha[3] = (R[3] - b1 * R[2] - b2 * R[1]) / alpha[0];

            alpha[2] = (R[2] - b1 * R[1] - b2 * R[0] - alpha[3] * (b1 * alpha[0] + alpha[1])) / ((alpha[0]));
            alpha[1] = (R[1] - b1 * R[0] - b2 * R[1] - alpha[2] * (b1 * alpha[0] + alpha[1]) - alpha[3] *
                    (b1 * (b1 * alpha[0] + alpha[1]) + b1 * alpha[0] + alpha[2])) / alpha[0];
            double tmp = R[0] - b1 * R[1] - b2 * R[2] - alpha[1] * (b1 * alpha[0] + alpha[1]) - alpha[2] * (b1 * (b1 * alpha[0] + alpha[1]) + b2 * alpha[0] + alpha[2])
                    - alpha[3] * (b1 * (b1 * (b1 * alpha[0] + alpha[1]) + b2 * alpha[0] + alpha[2]) + b2 * (b1 * alpha[0] + alpha[1]) + alpha[3]);
            if (tmp < 0) return new double[]{0};
            else alpha[0] = Math.sqrt(tmp);
            i++;
        }
        return alpha;

    }
    public double[] M3N1() {

        double[][] a = new double[][]{
                {R[1], R[0], R[1], R[2]},
                {R[2], R[1], R[0], R[3]},
                {R[3], R[2], R[1], R[4]}
        };
        double[] x = solveSystem(a);
        double[] alpha = new double[3];
        alpha[0] = Math.sqrt(R[0]);
        int i = 0;
        double b1 = x[0];
        double b2 = x[1];
        double b3 = x[2];
        while (i < 100) {
            alpha[1] = (R[1]-b1*R[0]-b2*R[1]-b3*R[2])/alpha[0];

            double tmp = R[0]-b1*R[1]-b2*R[2]-b3*R[3]-alpha[1]*(b1*alpha[0]+alpha[1]);
            if (tmp < 0) return new double[]{0};
            else alpha[0] = Math.sqrt(tmp);
            i++;
        }
        return alpha;

    }
    public double[] M3N2() {

        double[][] a = new double[][]{
                {R[2], R[1], R[0], R[3]},
                {R[3], R[2], R[1], R[4]},
                {R[4], R[3], R[2], R[5]}
        };
        double[] x = solveSystem(a);
        double[] alpha = new double[3];
        alpha[0] = Math.sqrt(R[0]);
        int i = 0;
        double b1 = x[0];
        double b2 = x[1];
        double b3 = x[2];
        while (i < 100) {
            alpha[2] = (R[2] - b1 * R[1] - b2 * R[0] - b3 * R[1]) / alpha[0];
            alpha[1] = (R[1] - b1 * R[0] - b2 * R[1] - b3 * R[2] - alpha[2] * b1 * alpha[0]) / ((alpha[0] + alpha[2]));

            double tmp = R[0] - b1 * R[1] - b2 * R[2] - b3 * R[3] - alpha[1] * (b1 * alpha[0] + alpha[1]) - alpha[2] * (b1 * (b1 * alpha[0] + alpha[1]) + b2 * alpha[0] + alpha[2]);
            if (tmp < 0) return new double[]{0};
            else alpha[0] = Math.sqrt(tmp);
            i++;
        }
        return alpha;

    }
    public double[] M3N2M2(){
        double[][] a = new double[][]{
                {R[2], R[1], R[0], R[3]},
                {R[3], R[2], R[1], R[4]},
                {R[4], R[3], R[2], R[5]}
        };
        double[] x = solveSystem(a);
        int i = 0;

        double alpha1 = 0;
        double alpha2 = 0;
        double alpha3 = 0;

        double eps = 0.0001;
        double b1 = x[0];
        double b2 = x[1];
        double b3 = x[2];
        double [] res=new double[10];
        for(int m = 0;m<10;m++){
            res[m]=R[m]-b1*R[Math.abs(m-1)]-b2*R[Math.abs(m-2)]-b3*R[Math.abs(m-3)];
            System.out.println("R["+m+"]="+R[m]+"-"+b1+" "+ R[Math.abs(m-1)]+"-"+b2+" "+R[Math.abs(m-2)]+"-"+b3+" "+R[Math.abs(m-3)]);
        }
        double alpha0 = Math.sqrt(res[0]);
        while (Math.abs(Math.pow(alpha0, 2) + Math.pow(alpha1, 2) + Math.pow(alpha2, 2) + Math.pow(alpha3, 2) - res[0]) > eps ||
                Math.abs(alpha0*alpha1+alpha1*alpha2+alpha2*alpha3-res[1]) > eps ||
                Math.abs(alpha0*alpha2+alpha1*alpha3-res[2]) > eps ||
                Math.abs(alpha0*alpha3-res[3]) > eps) {
            alpha3 = res[3]/alpha0;
            alpha2 = (res[2]-alpha1*alpha3)/alpha0;
            alpha1 = (res[1]-alpha1*alpha2-alpha2*alpha3)/alpha0;
            if(res[0] < Math.pow(alpha1, 2)+Math.pow(alpha2, 2)+Math.pow(alpha3, 2)) {
                return new double[4];
            } else {
                alpha0 = Math.sqrt(res[0]-Math.pow(alpha1, 2)-Math.pow(alpha2, 2)-Math.pow(alpha3, 2));
            }
        }
        System.out.println("a0="+alpha0);
        System.out.println("a1="+alpha1);
        System.out.println("a2="+alpha2);
        System.out.println("a3="+alpha3);





        return res;
    }

}
