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
        try{
            Robot.init(this,hardwareMap);
            Omnidirectional.Init(14.5f,19,3.8f );
            waitForStart();
            PowerManager.setTargets(0.0f,0.0f,0.0f,0.0f);
            PowerManager.start();
            float a = 0;
            while (opModeIsActive()){
//                Omnidirectional.setVelocity(100*(float)Math.cos(a),100*(float)Math.sin(a),0f,0.4f);
//                a+=Math.PI/4;
//                telemetry.addData("LF ",Omnidirectional.Motors.W1);
//                telemetry.addData("LB ",Omnidirectional.Motors.W2);
//                telemetry.addData("RB ",Omnidirectional.Motors.W3);
//                telemetry.addData("RF ",Omnidirectional.Motors.W4);
//                telemetry.addData("LF ",Omnidirectional.Motors.P1);
//                telemetry.addData("LB ",Omnidirectional.Motors.P2);
//                telemetry.addData("RB ",Omnidirectional.Motors.P3);
//                telemetry.addData("RF ",Omnidirectional.Motors.P4);
//                telemetry.addData("LF ",PowerManager.Motors.LF);
//                telemetry.addData("LB ",PowerManager.Motors.RB);
//                telemetry.addData("RB ",PowerManager.Motors.RF);
//                telemetry.addData("RF ",PowerManager.Motors.LB);
//                telemetry.addData("LF ",PowerManager.Motors.tLF);
//                telemetry.addData("LB ",PowerManager.Motors.tRB);
//                telemetry.addData("RB ",PowerManager.Motors.tRF);
//                telemetry.addData("RF ",PowerManager.Motors.tLB);
//                telemetry.addData("LF ",PowerManager.Motors.dLF);
//                telemetry.addData("LB ",PowerManager.Motors.dRB);
//                telemetry.addData("RB ",PowerManager.Motors.dRF);
//                telemetry.addData("RF ",PowerManager.Motors.dLB);

                PowerManager.setTargets((float)Math.sin(a),(float)Math.sin(a),(float)Math.sin(a),(float)Math.sin(a));
                a+=PI/10;
                telemetry.update();
                sleep(100 );

            }
            PowerManager.reset();
        }catch (Exception e){
            telemetry.addData(e.getStackTrace().toString(),0);
            telemetry.update();
        }


    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
