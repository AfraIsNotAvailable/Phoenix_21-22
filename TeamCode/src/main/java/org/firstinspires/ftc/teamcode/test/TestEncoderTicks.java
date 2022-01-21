package org.firstinspires.ftc.teamcode.test;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.DcMotorServo;
import org.firstinspires.ftc.teamcode.Omnidirectional;
import org.firstinspires.ftc.teamcode.OpModeAddition;
import org.firstinspires.ftc.teamcode.PowerManager;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * This 2020-2021 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the Ultimate Goal game elements.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@Autonomous(name="TestEncoderTicks", group="Pushbot")

public class TestEncoderTicks extends LinearOpMode implements OpModeAddition{

    @Override
    public void runOpMode()  {

//        Robot.init(this,hardwareMap);
//        Omnidirectional.Init(14.5f,19,3.8f );
           waitForStart();
        ElapsedTime rt = new ElapsedTime();
//        rt.reset();
//        PowerManager.setTargets(0.0f,0.0f,0.0f,0.0f);
//        PowerManager.step = 0.5f;
//        PowerManager.start();

        DcMotorServo motor = new DcMotorServo(hardwareMap,"motorRB",60,28);
        float angle = 0;
        double t = rt.milliseconds() + 200;
        while (opModeIsActive()){

//            int ticks = Robot.Motors.RB.getCurrentPosition();
//            if(gamepad1.a)
//                PowerManager.setTargets(1f,1f,1f,1f);
//            else
//                PowerManager.setTargets(0.0f,0.0f,0.0f,0.0f);
//            telemetry.addData("angle", 360*(ticks/(4f*72f)));
//            telemetry.addData("ticks", ticks);
//
//            telemetry.update();

//            if(rt.milliseconds() > t){
//
//
//                angle += gamepad1.left_bumper ? 1 : 0;
//                angle -= gamepad1.right_bumper ? 1 : 0;
//                t = rt.milliseconds() + 100;
//            }
//            motor.setAngle(angle,0.2f);
//            telemetry.addData("a",angle);
//            telemetry.addData("bo",((DcMotorControllerEx)motor.motor.getController()).getMotorCurrentAlert(motor.motor.getPortNumber(), CurrentUnit.AMPS));
//            telemetry.addData("bo2",((DcMotorControllerEx)motor.motor.getController()).getMotorCurrent(motor.motor.getPortNumber(), CurrentUnit.AMPS));

            motor.setAngle(-180*gamepad1.right_trigger,1f);
            telemetry.addData("f",motor.motor.getTargetPosition());
            telemetry.addData("f2",motor.motor.getCurrentPosition());
            telemetry.update();
//            motor.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            motor.motor.setPower(gamepad1.left_trigger-gamepad1.right_trigger);
            //motor.motor.setPower(1);
        }
  //      PowerManager.reset();



    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
