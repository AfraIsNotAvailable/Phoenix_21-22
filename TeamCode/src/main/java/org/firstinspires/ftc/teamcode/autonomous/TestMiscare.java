package org.firstinspires.ftc.teamcode.autonomous;


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
            Omnidirectional.Init(21,15,3.8f );
            waitForStart();
//        PowerManager.setTargets(0.5f,0.5f,0.5f,0.5f);
            PowerManager.start();
            Omnidirectional.setVelocity(10,10);
            while (opModeIsActive()){
                telemetry.addData("LF ",PowerManager.Motors.LF);
                telemetry.addData("LB ",PowerManager.Motors.LB);
                telemetry.addData("RB ",PowerManager.Motors.RB);
                telemetry.addData("RF ",PowerManager.Motors.RF);
                telemetry.update();
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
