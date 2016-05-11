/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 *
 * @author Павел
 */
public class solveNonLinear {
    public static double[] fourEquations(double[] R){
        double[] alpha = new double[4];
        alpha[0] = sqrt(R[0]);
        alpha[1] = 0;
        alpha[2] = 0;
        alpha[3] = 0;
        double epsilon =0.001;
        while (abs(pow(alpha[0], 2) + pow(alpha[1], 2) + pow(alpha[2],2) + pow(alpha[3],2) - R[0]) > epsilon || 
                abs(alpha[0]*alpha[1] + alpha[1]*alpha[2] + alpha[2]*alpha[3] -R[1]) > epsilon ||
                abs(alpha[0]*alpha[2] + alpha[1]*alpha[3] - R[2]) > epsilon ||
                abs(alpha[0]*alpha[3] - R[3]) > epsilon) {
//        while(sqrt(pow(R[0]-pow(alpha[0],2)-pow(alpha[1],2)-pow(alpha[2],2)-pow(alpha[3],2),2)
//        +pow(R[1]-alpha[0]*alpha[1]-alpha[1]*alpha[2]-alpha[2]*alpha[3],2)
//        +pow(R[2]-alpha[0]*alpha[2]-alpha[1]*alpha[3],2)
//        +pow(R[3]-alpha[0]*alpha[3],2))>epsilon)
//        {
            alpha[3] = R[3]/alpha[0];
            alpha[2] = (R[2] - alpha[1]*alpha[3])/alpha[0];
            alpha[1] = (R[1] - alpha[1]*alpha[2] - alpha[2]*alpha[3])/alpha[0];
            if(R[0] < pow(alpha[1], 2)+pow(alpha[2],2) +pow(alpha[3],2)) {
                alpha = new double[] {0,0,0,0};
                break;
            } else {
                alpha[0] = sqrt(R[0]-pow(alpha[1], 2)-pow(alpha[2],2)-pow(alpha[3],2));
            }
        }
        return alpha;
    }
    
    public static double[] threeEquations(double[] R){
        double[] alpha = new double[3];
        alpha[0] = sqrt(R[0]);
        alpha[1] = 0;
        alpha[2] = 0;
        double epsilon = 0.0001;
        while (abs(pow(alpha[0], 2) + pow(alpha[1], 2) + pow(alpha[2],2) - R[0]) > epsilon || 
                abs(alpha[0]*alpha[1] + alpha[1]*alpha[2] -R[1]) > epsilon ||
                abs(alpha[0]*alpha[2] - R[2]) > epsilon) {
            alpha[2] = R[2]/alpha[0];
            alpha[1] = (R[1] - alpha[1]*alpha[2])/alpha[0];
            if(R[0] < pow(alpha[1], 2)+pow(alpha[2],2)) {
                alpha = new double[] {0,0,0};
                break;
            } else {
                alpha[0] = sqrt(R[0]-pow(alpha[1], 2)-pow(alpha[2],2));
            }
        }
        return alpha;
    }
    
    public static double[] twoEquations(double[] R){
        double[] alpha = new double[2];
        alpha[0] = sqrt(R[0]);
        alpha[1] = 0;
        double epsilon = 0.0001;
        while (abs(pow(alpha[0], 2) + pow(alpha[1], 2) - R[0]) > epsilon || 
                abs(alpha[0]*alpha[1]-R[1]) > epsilon) {
            alpha[1] = R[1]/alpha[0];
            if(R[0] < pow(alpha[1], 2)) {
                alpha = new double[]{0,0};
                break;
            } else {
                alpha[0] = sqrt(R[0]-pow(alpha[1], 2));
            }
        }
        return alpha;
    }
    
    public static double[] oneEquation(double[] R){
        double[] alpha = new double[]{sqrt(R[0])};
        return alpha;
    }
}
