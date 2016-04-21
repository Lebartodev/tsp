/**
 * Created by Sasha on 18.04.2016.
 */
public class SS {
    private double Rn[];
    private double rk[];
    private double[] NCF0 = new double[11];
    private double[] NCF1 = new double[11];
    private double[] NCF2 = new double[11];
    private double[] NCF3 = new double[11];
    public SS(double[] Rn,double rk[]){
        this.Rn=Rn;
        this.rk=rk;


    }
    public void calcSS0(){
        System.out.println("SS 0");
        System.out.println("a = "+Math.sqrt(Rn[0]) );
        NCF0[0]= 1;
        NCF0[1]= 0;
        for(int i = 2;i<NCF0.length;i++){
            NCF0[i] = 0;
        }
        double eps=0;
        for(int i =1;i<NCF0.length;i++){
            eps += Math.pow(NCF0[i] - rk[i], 2);
        }
        System.out.println("eps = "+eps);

    }
    public void calcSS1() {
        System.out.println("SS 1");
        double alpha0 = Math.sqrt(Rn[0]);
        double alpha1 = 0;

        double eps = 0.0001;

        while (Math.abs(Math.pow(alpha0, 2) + Math.pow(alpha1, 2) - Rn[0]) > eps ||
                Math.abs(alpha0*alpha1-Rn[1]) > eps) {
            alpha1 = Rn[1]/alpha0;
            if(Rn[0] < Math.pow(alpha1, 2)) {
                return;
            } else {
                alpha0 = Math.sqrt(Rn[0]-Math.pow(alpha1, 2));
            }
        }

        System.out.println(
                "a0 = "+alpha0
        );
        System.out.println(
                "a1 = "+alpha1
        );
        NCF1[0]= 1;
        NCF1[1]= rk[1];
        for(int i = 2;i<NCF1.length;i++){
            NCF1[i] = 0;
        }
        double epsCalc=0;
        for(int i =1;i<NCF1.length;i++){
            epsCalc += Math.pow(NCF1[i] - rk[i], 2);
        }
        System.out.println("eps = "+epsCalc);
    }

    public void calcSS2() {
        System.out.println("SS 2");
        double alpha0 = Math.sqrt(Rn[0]);
        double alpha1 = 0;
        double alpha2 = 0;

        double eps = 0.0001;

        while (Math.abs(Math.pow(alpha0, 2) + Math.pow(alpha1, 2) + Math.pow(alpha2, 2) - Rn[0]) > eps ||
                Math.abs(alpha0*alpha1+alpha1*alpha2-Rn[1]) > eps ||
                Math.abs(alpha0*alpha2-Rn[2]) > eps) {
            alpha2 = Rn[2]/alpha0;
            alpha1 = (Rn[1]-alpha1*alpha2)/alpha0;
            if(Rn[0] < Math.pow(alpha1, 2)+Math.pow(alpha2, 2)) {
                return;
            } else {
                alpha0 = Math.sqrt(Rn[0]-Math.pow(alpha1, 2)-Math.pow(alpha2, 2));
            }
        }
        System.out.println(
                "a0 = "+alpha0
        );
        System.out.println(
                "a1 = "+alpha1
        );
        System.out.println(
                "a2 = "+alpha2
        );
        NCF2[0]= 1;
        NCF2[1]= rk[1];
        NCF2[2]= rk[2];
        for(int i = 3;i<NCF2.length;i++){
            NCF2[i] = 0;
        }
        double epsCalc=0;
        for(int i =1;i<NCF2.length;i++){
            epsCalc += Math.pow(NCF2[i] - rk[i], 2);
        }
        System.out.println("eps = "+epsCalc);

    }

    public void calcSS3() {
        System.out.println("SS 3");
        double alpha0 = Math.sqrt(Rn[0]);
        double alpha1 = 0;
        double alpha2 = 0;
        double alpha3 = 0;

        double eps = 0.0001;

        while (Math.abs(Math.pow(alpha0, 2) + Math.pow(alpha1, 2) + Math.pow(alpha2, 2) + Math.pow(alpha3, 2) - Rn[0]) > eps ||
                Math.abs(alpha0*alpha1+alpha1*alpha2+alpha2*alpha3-Rn[1]) > eps ||
                Math.abs(alpha0*alpha2+alpha1*alpha3-Rn[2]) > eps ||
                Math.abs(alpha0*alpha3-Rn[3]) > eps) {
            alpha3 = Rn[3]/alpha0;
            alpha2 = (Rn[2]-alpha1*alpha3)/alpha0;
            alpha1 = (Rn[1]-alpha1*alpha2-alpha2*alpha3)/alpha0;
            if(Rn[0] < Math.pow(alpha1, 2)+Math.pow(alpha2, 2)+Math.pow(alpha3, 2)) {
                return;
            } else {
                alpha0 = Math.sqrt(Rn[0]-Math.pow(alpha1, 2)-Math.pow(alpha2, 2)-Math.pow(alpha3, 2));
            }
        }
        System.out.println(
                "a0 = "+alpha0
        );
        System.out.println(
                "a1 = "+alpha1
        );
        System.out.println(
                "a2 = "+alpha2
        );
        System.out.println(
                "a3 = "+alpha3
        );
        NCF3[0]= 1;
        NCF3[1]= rk[1];
        NCF3[2]= rk[2];
        NCF3[3]= rk[3];
        for(int i = 4;i<NCF3.length;i++){
            NCF3[i] = 0;
        }
        double epsCalc=0;
        for(int i =1;i<NCF3.length;i++){
            epsCalc += Math.pow(NCF3[i] - rk[i], 2);
        }
        System.out.println("eps = "+epsCalc);
    }

}
