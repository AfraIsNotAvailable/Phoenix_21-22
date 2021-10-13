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
    }

    public static float step = 0.015f;
    public static float nanosecond_step = 100000000.f * 0.95f;
    public static boolean bRun = true;

    public static void setStep(float ns) {
        step = ns;
    }

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
            float mint = Math.min(Math.min(Motors.tLF, Motors.tLB),Math.min(Motors.tLF, Motors.tLB));


            while(bRun) {
                long startTime = System.nanoTime();

                if(Math.abs(Motors.LF - Motors.tLF) > Math.abs(0.001)) {
                    Motors.LF += Motors.sLF * (dt * step * ((1+ ((1 - Motors.sLF)/2)) * Math.abs(Motors.tLF))/mint);
                }
                if(Math.abs(Motors.RF - Motors.tRF) > Math.abs(0.001)) {
                    Motors.RF += Motors.sRF * (dt * step * ((1+ ((1 - Motors.sRF)/2)) * Math.abs(Motors.tRF))/mint);
                }
                if(Math.abs(Motors.LB - Motors.tLB) > Math.abs(0.001)) {
                    Motors.LB += Motors.sLB * (dt * step * ((1+ ((1 - Motors.sLB)/2)) * Math.abs(Motors.tLB))/mint);
                }
                if(Math.abs(Motors.RB - Motors.tRB) > Math.abs(0.001)) {
                    Motors.RB += Motors.sRB * (dt * step * ((1+ ((1 - Motors.sRB)/2)) * Math.abs(Motors.tRB))/mint);
                }

                try {
                    Thread.sleep(0,(int)Math.random()%500 + 500);//simulating slowness
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                float et = ((float)System.nanoTime() - (float)startTime);
                dt = et/nanosecond_step;

//                debugPrintTargets();
                Robot.Motors.RB.setPower(Motors.RB);
                Robot.Motors.RF.setPower(Motors.RF);
                Robot.Motors.LB.setPower(Motors.LB);
                Robot.Motors.LF.setPower(Motors.LF);
            }
        }, (long)0, TimeUnit.MICROSECONDS);
    }
}