package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Movement;
import org.firstinspires.ftc.teamcode.OmniSimple;
import org.firstinspires.ftc.teamcode.Omnidirectional;
import org.firstinspires.ftc.teamcode.OpModeAddition;
import org.firstinspires.ftc.teamcode.Robot;
@TeleOp(name="ControlDemo", group="Control")

public class ControlDemo extends LinearOpMode implements OpModeAddition {


    @Override
    public void runOpMode() throws InterruptedException {
        Robot.init(this,hardwareMap);
        Robot.Motors.LIFT.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();

        ElapsedTime bratUp = new ElapsedTime();
        ElapsedTime bratDown = new ElapsedTime();
        double upLim = 0;
        double downLim = 0;
        float bratAng = 0;
        if (isStopRequested()) return;

        while (opModeIsActive()){
            /*if((Math.abs(gamepad1.right_stick_y) > 0.1 || Math.abs(gamepad1.right_stick_x) > 0.1) || Math.abs(gamepad1.left_stick_x) > 0.1) {
                //omni
                double[] debug = OmniSimple.calculateAndSet(gamepad1.right_stick_x* 80, gamepad1.right_stick_y * 80, gamepad1.left_stick_x * 3.1415f);
            }else{
                if(-gamepad1.left_trigger<-0.05)
                    Movement.setPowerForced(-gamepad1.left_trigger);
                else if(gamepad1.right_trigger>0.05)
                    Movement.setPowerForced(gamepad1.right_trigger);
                else
                    Movement.setPowerForced(0);
            }*/

            double speed = gamepad1.right_trigger - gamepad1.left_trigger;
            double direction = gamepad1.left_stick_x;

            double right = speed + direction;
            double left = speed - direction;

            if (right > 1)
                right = 1;
            if (right < -1)
                right = -1;
            if (left > 1)
                left = 1;
            if (left < -1)
                left = -1;

            if (gamepad1.right_stick_x != 0) {
                Robot.Motors.LB.setPower(-gamepad1.right_stick_x);
                Robot.Motors.LF.setPower(gamepad1.right_stick_x);
                Robot.Motors.RB.setPower(gamepad1.right_stick_x);
                Robot.Motors.RF.setPower(-gamepad1.right_stick_x);
            }
            else {
                Robot.Motors.LB.setPower(left);
                Robot.Motors.LF.setPower(left);
                Robot.Motors.RB.setPower(right);
                Robot.Motors.RF.setPower(right);
            }

            //leeft
            Robot.Motors.LIFT.setAngle(bratAng,0.7f);


            if(gamepad2.a && bratDown.milliseconds() > downLim){
                downLim = bratDown.milliseconds() + 100;
                bratAng += 170;
            }

            if(gamepad2.y && bratUp.milliseconds() > upLim){
                upLim = bratUp.milliseconds() + 100;
                bratAng -= 170;
            }

            if(gamepad2.x)
                Robot.Motors.CARUSEL.setPower(-0.4);
            else if(gamepad2.b)
                Robot.Motors.CARUSEL.setPower(0.4);
            else
                Robot.Motors.CARUSEL.setPower(0);

            //intec
            if(gamepad2.right_trigger > 0.1 && gamepad2.right_trigger <= 0.5){
                Robot.Motors.INTAKE.setPower(-0.5);
            }else if(gamepad2.right_trigger > 0.5 && gamepad2.right_trigger <= 1){
                Robot.Motors.INTAKE.setPower(-1);
            }else{
                Robot.Motors.INTAKE.setPower(0);
            }

            if(gamepad2.right_bumper)
                Robot.Motors.INTAKE.setPower(1);

            //servou

            Robot.CUPA.setPosition(1 - gamepad2.left_stick_y*0.5);



//            telemetry.addData("w1",debug[0]);
//            telemetry.addData("w2",debug[1]);
//            telemetry.addData("w3",debug[2]);
//            telemetry.addData("w4",debug[3]);
            telemetry.addData("encoder",Robot.Motors.LIFT.motor.getCurrentPosition());
            telemetry.addData("ul",upLim);
            telemetry.addData("ut",bratUp.milliseconds());
            telemetry.addData("dl",downLim);
            telemetry.addData("dt",bratDown.milliseconds());
            telemetry.update();

        }
    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
