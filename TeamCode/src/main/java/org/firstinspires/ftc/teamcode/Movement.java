package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Movement {


    //da aceeasi putere la motoare
    public static void setPower(float p){
        PowerManager.setTargets(p,p,p,p);
    }
    //puteri diferite
    public static void setPower(float lf, float rf, float lb, float rb){
        PowerManager.setTargets(lf,rf,lb,rb);
    }
    //seteaza targetul la motoare
    public static void setTarget(int p){
        Robot.Motors.LB.setTargetPosition(p);
        Robot.Motors.LF.setTargetPosition(p);
        Robot.Motors.RB.setTargetPosition(p);
        Robot.Motors.RF.setTargetPosition(p);
    }
    //setreaza targetul
    public static void setTarget(int lf, int rf, int lb, int rb){
        Robot.Motors.LF.setTargetPosition(lf);
        Robot.Motors.RF.setTargetPosition(rf);
        Robot.Motors.LB.setTargetPosition(lb);
        Robot.Motors.RB.setTargetPosition(rb);
    }

    public static void incrementTarget(int lf, int rf, int lb, int rb){
        Robot.Motors.LF.setTargetPosition(Robot.Motors.LF.getCurrentPosition() + lf);
        Robot.Motors.RF.setTargetPosition(Robot.Motors.RF.getCurrentPosition() + rf);
        Robot.Motors.LB.setTargetPosition(Robot.Motors.LB.getCurrentPosition() + lb);
        Robot.Motors.RB.setTargetPosition(Robot.Motors.RB.getCurrentPosition() + rb);
    }

    public static void setRunMode(DcMotor.RunMode runMode){
        Robot.Motors.LF.setMode(runMode);
        Robot.Motors.RF.setMode(runMode);
        Robot.Motors.LB.setMode(runMode);
        Robot.Motors.RB.setMode(runMode);
    }
    //motoarele is capabile sa tot mearga pana ajung la target
    //bascially functia asta face, le da target la motoare ca sa ajunga colo
    public static void driveCM(int cm, float Speed, int cur,int finish){

        int Target = (int)((cm/(2*Math.PI*5.01)) * 28 * 18);
        if(Target < 0) Speed = Speed*(-1);

        Robot.Motors.LB.setTargetPosition(Robot.Motors.LB.getCurrentPosition() + Target);
        Robot.Motors.RB.setTargetPosition(Robot.Motors.RB.getCurrentPosition() + Target);
        Robot.Motors.LF.setTargetPosition(Robot.Motors.LF.getCurrentPosition() + Target);
        Robot.Motors.RF.setTargetPosition(Robot.Motors.RF.getCurrentPosition() + Target);

        Robot.Motors.RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.Motors.LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.Motors.RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.Motors.LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setPowerForced(Speed);

        while(Robot.Motors.LB.isBusy() && Robot.Motors.RB.isBusy() && Robot.Motors.LF.isBusy() && Robot.Motors.RF.isBusy()){
            setPowerForced(Speed);
        }

        setPowerForced(0);

        Robot.Motors.LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Motors.RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Motors.LB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Motors.RB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Motors.LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.Motors.RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.Motors.LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.Motors.RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        setTarget(target);
//        setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
//        setPowerForced(power);
//        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

//        if( Robot.Motors.LF.isBusy() &&
//            Robot.Motors.RF.isBusy() &&
//            Robot.Motors.RB.isBusy() &&
//            Robot.Motors.LB.isBusy() &&
//            Robot.opMode.isOpModeIsActive()
//        ){
//            setPowerForced(power);
//            return cur;
//        }
//        else{
//            setPowerForced(0);
//            setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//            return finish;
//        }

    }

    public static void slideCM(int cm, float Speed, int cur,int finish){
        int sign = 1;
        int Target = (int)((cm/(2*Math.PI*5.01)) * 28 * 20);
        if(Target < 0) Speed = Speed*(-1);
        if(Target < 0) sign = -1;

        Robot.Motors.LB.setTargetPosition(Robot.Motors.LB.getCurrentPosition() - Target);
        Robot.Motors.RB.setTargetPosition(Robot.Motors.RB.getCurrentPosition() + Target);
        Robot.Motors.LF.setTargetPosition(Robot.Motors.LF.getCurrentPosition() + Target);
        Robot.Motors.RF.setTargetPosition(Robot.Motors.RF.getCurrentPosition() - Target);

        Robot.Motors.RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.Motors.LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.Motors.RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.Motors.LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setPowerForced(Speed);

        while(Robot.Motors.LB.isBusy() && Robot.Motors.RB.isBusy() && Robot.Motors.LF.isBusy() && Robot.Motors.RF.isBusy()){
            setPowerForced(Speed);
        }

        setPowerForced(0);

        Robot.Motors.LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Motors.RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Motors.LB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Motors.RB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Motors.LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.Motors.RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.Motors.LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.Motors.RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



    }

    public static void turn(float angle, float  Speed){
        int Target = (int)(2157 * (angle/327.5));
        int sign = 1;

        if(Target < 0) Speed = Speed*(-1);
        if(Target < 0) sign = -1;

        Robot.Motors.LB.setTargetPosition(Robot.Motors.LB.getCurrentPosition() + Target);
        Robot.Motors.RB.setTargetPosition(Robot.Motors.RB.getCurrentPosition() - Target);
        Robot.Motors.LF.setTargetPosition(Robot.Motors.LF.getCurrentPosition() + Target);
        Robot.Motors.RF.setTargetPosition(Robot.Motors.RF.getCurrentPosition() - Target);

        Robot.Motors.RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.Motors.LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.Motors.RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.Motors.LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setPowerForced(Speed);

        while(Robot.Motors.LB.isBusy() && Robot.Motors.RB.isBusy() && Robot.Motors.LF.isBusy() && Robot.Motors.RF.isBusy()){
            setPowerForced(Speed);
        }

        setPowerForced(0);

        Robot.Motors.LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Motors.RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Motors.LB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Motors.RB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Motors.LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.Motors.RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.Motors.LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.Motors.RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    //initializeaza un thread nou care verifica constant daca conditia s-a indeplinit
    //odata ce se indeplineste opreste motoarele
    public static void runWithCondition(float power, Runnable condition){
        Robot.scheduler.schedule(condition, 0,TimeUnit.MILLISECONDS); //de parca ne permitem microseconds lol
        setPower(power);
    }

    public static void runForTime(float milliseconds,float power){
        ElapsedTime t = new ElapsedTime();
        t.reset();
        runWithCondition(power, () -> {
            while ((t.milliseconds() < milliseconds)) {}
            setPower(0);
        });
    }

    public static void setPowerForced(float p){
        setPowerForced(p,p,p,p);
    }
    //puteri diferite
    public static void setPowerForced(float lf, float rf, float lb, float rb){
        Robot.Motors.LF.setPower(lf);
        Robot.Motors.RF.setPower(rf);
        Robot.Motors.LB.setPower(lb);
        Robot.Motors.RB.setPower(rb);
    }

    public static void setPowerForced(double lf, double rf, double lb, double rb){
        Robot.Motors.LF.setPower(lf);
        Robot.Motors.RF.setPower(rf);
        Robot.Motors.LB.setPower(lb);
        Robot.Motors.RB.setPower(rb);
    }

    public static void setVelocityForced(float lf, float rf, float lb, float rb){
        Robot.Motors.LF.setVelocity(lf, AngleUnit.RADIANS);
        Robot.Motors.RF.setVelocity(rf, AngleUnit.RADIANS);
        Robot.Motors.LB.setVelocity(lb, AngleUnit.RADIANS);
        Robot.Motors.RB.setVelocity(rb, AngleUnit.RADIANS);
    }

    public static void setVelocityForced(double lf, double rf, double lb, double rb){
        Robot.Motors.LF.setVelocity(lf, AngleUnit.RADIANS);
        Robot.Motors.RF.setVelocity(rf, AngleUnit.RADIANS);
        Robot.Motors.LB.setVelocity(lb, AngleUnit.RADIANS);
        Robot.Motors.RB.setVelocity(rb, AngleUnit.RADIANS);
    }


}
