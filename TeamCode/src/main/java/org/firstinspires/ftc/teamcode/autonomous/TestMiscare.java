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
                Omnidirectional.setVelocity(gamepad1.right_stick_x,gamepad1.right_stick_y,0f,1.f);
//                a+=Math.PI/4;


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
