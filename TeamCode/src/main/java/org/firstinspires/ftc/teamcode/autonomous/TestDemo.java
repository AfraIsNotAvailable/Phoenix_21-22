package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Movement;
import org.firstinspires.ftc.teamcode.OpModeAddition;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name="Auto1", group="Pushbot")

public class TestDemo extends LinearOpMode implements OpModeAddition {
    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime clk = new ElapsedTime();

        int stage = 0;
        Robot.init(this,hardwareMap);
        waitForStart();
        Movement.driveCM(5,0.5f,0,0);
        Movement.turn(-93,0.5f);
        Movement.driveCM(-52,0.5f,0,0);
        double ct = clk.milliseconds();
        Robot.Motors.CARUSEL.setPower(-0.4);
        while (clk.milliseconds() < ct + 2000){}
        Movement.turn(45,0.5f);
        Movement.driveCM((int)(1.25*60*Math.sqrt(2)),0.5f,0,0);
        Movement.turn(-90,0.5f);
        Movement.driveCM((int)(0.8*60*Math.sqrt(2)),0.5f,0,0);
        Movement.turn(70,0.5f);
        //Movement.driveCM((int)(0.8*60*Math.sqrt(2)),0.5f,0,0);
        //drop
//        Movement.slideCM(85,0.2f,1,1);
//        double ct = clk.milliseconds();
//        Robot.Motors.CARUSEL.setPower(-0.4);
//        while (clk.milliseconds() < ct + 2000){}
//        Movement.setPowerForced(0.5,-0.5,0.5,-0.5);
//        while(clk.milliseconds() < 2000.0){
//
//        }
//        Movement.setPowerForced(0);
//
//        clk.reset();
//        while (clk.milliseconds() < 10000.0){
//            telemetry.addData("t1",Robot.Motors.LF.getCurrentPosition());
//            telemetry.addData("t2",Robot.Motors.RF.getCurrentPosition());
//            telemetry.addData("t3",Robot.Motors.LB.getCurrentPosition());
//            telemetry.addData("t4",Robot.Motors.RB.getCurrentPosition());
//            telemetry.update();
//        }

        //Movement.turn(90,0.5f);

    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
