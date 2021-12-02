package org.firstinspires.ftc.teamcode;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PowerManager {



    public static class Motors{
        public static float LF = 0; //1 pe poza
        public static float RF = 0; //2 pe poza
        public static float LB = 0; //3 pe poza
        public static float RB = 0; //4 pe poza

        public static float tLF = 0; //1 pe poza
        public static float tRF = 0; //2 pe poza
        public static float tLB = 0; //3 pe poza
        public static float tRB = 0; //4 pe poza

        public static float sLF = 1;
        public static float sRF = 1;
        public static float sLB = 1;
        public static float sRB = 1;

        public static float dLF = 1;
        public static float dRF = 1;
        public static float dLB = 1;
        public static float dRB = 1;

        public static float wLF = 1;
        public static float wRF = 1;
        public static float wLB = 1;
        public static float wRB = 1;
    }

    public static float step = 0.2f;
    public static float nanosecond_step = 100000000.f * 0.95f;
    public static float delta_step = 10 ;
    public static boolean bRun = true;

    public static void setStep(float ns) {
        step = ns;
    }

    public static float weigth = 1;
    public static void setTargets(float tlf, float trf, float tlb, float trb) {



        Motors.tLF= tlf;
        Motors.tRF= trf;
        Motors.tLB= tlb;
        Motors.tRB= trb;

        if(tlf < Motors.LF)
            Motors.sLF = -1;
        else
            Motors.sLF = 1;

        if(trf< Motors.RF)
            Motors.sRF = -1;
        else
            Motors.sRF = 1;

        if(tlb < Motors.LB)
            Motors.sLB = -1;
        else
            Motors.sLB = 1;

        if(trb < Motors.RB)
            Motors.sRB = -1;
        else
            Motors.sRB = 1;

        PowerManager.weigth = Math.max(
                Math.max(
                        Math.abs(Motors.LF - Motors.tLF),Math.abs(Motors.LB - Motors.tLB)
                ),
                Math.max(
                        Math.abs(Motors.RF - Motors.tRF),Math.abs(Motors.RB - Motors.tRB)
                )
        );

        Motors.wLF = Math.abs(Motors.LF - Motors.tLF);
        Motors.wRF = Math.abs(Motors.RF - Motors.tRF);
        Motors.wLB = Math.abs(Motors.LB - Motors.tLB);
        Motors.wRB = Math.abs(Motors.RB - Motors.tRB);

    }

    public static void setPowersForced(float rb, float rf, float lb, float lf){
        Motors.RB = rb;
        Motors.LF = lf;
        Motors.LB = lb;
        Motors.RF = rf;
        Motors.tRB = rb;
        Motors.tLF = lf;
        Motors.tLB = lb;
        Motors.tRF = rf;
    }

    public static void debugPrintTargets() {
        System.out.printf("LF: %f tLF: %f ---- RF: %f tRF: %f ---- LB: %f tLB: %f ---- RB: %f tRB: %f\n",
                Motors.LF, Motors.sLF, Motors.RF, Motors.sRF, Motors.LB, Motors.sLB, Motors.RB, Motors.sRB);
    }

    public static void reset(){
        Motors.LB = Motors.LF = Motors.RB = Motors.RF = 0;
        Motors.tLB = Motors.tLF = Motors.tRB = Motors.tRF = 0;
        Motors.sLB = Motors.sLF = Motors.sRB = Motors.sRF = 0;
    }

    public static void start() {
        Robot.scheduler.schedule(() -> {

            float dt = 0;


            while(bRun) {
                //TODO check to see if it's better if calculated everytime or on setTargets
                Motors.dLF = Math.min(Math.max(Math.abs(Motors.tLF - Motors.LF) * delta_step,1),15);
                Motors.dRF = Math.min(Math.max(Math.abs(Motors.tRF - Motors.RF) * delta_step,1),15);
                Motors.dLB = Math.min(Math.max(Math.abs(Motors.tLB - Motors.LB) * delta_step,1),15);
                Motors.dRB = Math.min(Math.max(Math.abs(Motors.tRB - Motors.RB) * delta_step,1),15);
                long startTime = System.nanoTime();

                if(Math.abs(Motors.LF - Motors.tLF) > Math.abs(0.001)) {
                    Motors.LF += Motors.dLF * Motors.sLF * (dt * step * Motors.wLF/weigth);
                }
                if(Math.abs(Motors.RF - Motors.tRF) > Math.abs(0.001)) {
                    Motors.RF += Motors.dRF * Motors.sRF * (dt * step * Motors.wRF/weigth);
                }
                if(Math.abs(Motors.LB - Motors.tLB) > Math.abs(0.001)) {
                    Motors.LB += Motors.dLB * Motors.sLB * (dt * step * Motors.wLB/weigth);
                }
                if(Math.abs(Motors.RB - Motors.tRB) > Math.abs(0.001)) {
                    Motors.RB += Motors.dRB * Motors.sRB * (dt * step * Motors.wRB/weigth);
                }

                try {
                    Thread.sleep(0,(int)Math.random()%500 + 500);//simulating slowness
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                float et = ((float)System.nanoTime() - (float)startTime);
                dt = et/nanosecond_step;

                Robot.Motors.RB.setPower(Motors.RB);
                Robot.Motors.RF.setPower(Motors.RF);
                Robot.Motors.LB.setPower(Motors.LB);
                Robot.Motors.LF.setPower(Motors.LF);

            }
        }, (long)0, TimeUnit.MICROSECONDS);
    }
}