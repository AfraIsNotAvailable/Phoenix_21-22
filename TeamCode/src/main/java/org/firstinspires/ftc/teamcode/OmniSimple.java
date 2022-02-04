package org.firstinspires.ftc.teamcode;

public class OmniSimple {

    static float wheelRadius = 5.01f;
    static float a = 11.25f;
    static float b = 11.25f;

    public static double[] calculateAndSet(float Vy, float Vx, float W) {

        double W1 = Vx - Vy - (a + b) * W;
        double W2 = Vx + Vy + (a + b) * W;
        double W3 = Vx + Vy - (a + b) * W;
        double W4 = Vx - Vy + (a + b) * W;

        W1 *= 1/wheelRadius;
        W2 *= 1/wheelRadius;
        W3 *= 1/wheelRadius;
        W4 *= 1/wheelRadius;


        Movement.setVelocityForced(W1,W2,W3,W4);

        double[] d = new double[5];
        d[0] = W1;
        d[1] = W2;
        d[2] = W3;
        d[3] = W4;

        return d;
    }


}
