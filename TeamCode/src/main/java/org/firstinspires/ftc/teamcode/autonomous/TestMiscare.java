package org.firstinspires.ftc.teamcode.autonomous;


import static java.lang.Math.PI;

import org.firstinspires.ftc.teamcode.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

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
@Autonomous(name="TestMiscare", group="Pushbot")
public class TestMiscare extends LinearOpMode implements OpModeAddition{

    @Override
    public void runOpMode()  {

            Robot.init(this,hardwareMap);
            Omnidirectional.Init(14.5f,19,3.8f );
            waitForStart();
            ElapsedTime rt = new ElapsedTime();
            rt.reset();
            PowerManager.setTargets(0.0f,0.0f,0.0f,0.0f);
            PowerManager.start();
            long tm = 0;

            float a = 0;
            while (opModeIsActive()){
               Omnidirectional.setVelocity(gamepad1.right_stick_x,gamepad1.right_stick_y,0f,.5f);
//                a+=Math.PI/4;

                if(gamepad1.a){
                    PowerManager.setTargets(0.0f,0.0f,0.0f,0.0f);
                    Robot.Motors.RB.setPower(0);
                    Robot.Motors.LF.setPower(0);
                    Robot.Motors.LB.setPower(0);
                    Robot.Motors.RF.setPower(0);
                }

                telemetry.addData("wLF ",Omnidirectional.Motors.W1);
                telemetry.addData("wLB ",Omnidirectional.Motors.W2);
                telemetry.addData("wRB ",Omnidirectional.Motors.W3);
                telemetry.addData("wRF ",Omnidirectional.Motors.W4);
                telemetry.addData("pLF ",Omnidirectional.Motors.P1);
                telemetry.addData("pLB ",Omnidirectional.Motors.P2);
                telemetry.addData("pRB ",Omnidirectional.Motors.P3);
                telemetry.addData("pRF ",Omnidirectional.Motors.P4);
                telemetry.addData("LF ",PowerManager.Motors.LF);
                telemetry.addData("LB ",PowerManager.Motors.RB);
                telemetry.addData("RB ",PowerManager.Motors.RF);
                telemetry.addData("RF ",PowerManager.Motors.LB);
                telemetry.addData("tLF ",PowerManager.Motors.tLF);
                telemetry.addData("tLB ",PowerManager.Motors.tRB);
                telemetry.addData("tRB ",PowerManager.Motors.tRF);
                telemetry.addData("tRF ",PowerManager.Motors.tLB);
                telemetry.addData("dLF ",PowerManager.Motors.dLF);
                telemetry.addData("dLB ",PowerManager.Motors.dRB);
                telemetry.addData("dRB ",PowerManager.Motors.dRF);
                telemetry.addData("dRF ",PowerManager.Motors.dLB);

//                PowerManager.setTargets((float)Math.sin(a),(float)Math.sin(a),(float)Math.sin(a),(float)Math.sin(a));
//                if(rt.milliseconds()>tm){
//                    a+=0.01;
//                    tm+=10;
//                }

                telemetry.update();
                //sleep(100 );

            }
            PowerManager.reset();



    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
