package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

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
    public static void driveToTarget(int target, float power){
        setTarget(target);
        setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
        setPower(power);
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
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



}
