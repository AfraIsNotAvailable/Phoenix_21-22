package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

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
@Autonomous(name="TestMiscare2", group="Pushbot")
@Disabled
public class TestMiscare2 extends LinearOpMode implements OpModeAddition{

    @Override
    public void runOpMode()  {

            Robot.init(this,hardwareMap);
            Omnidirectional.Init(14.5f,19,3.8f );
            waitForStart();
            ElapsedTime rt = new ElapsedTime();
            rt.reset();
            PowerManager.setTargets(0.0f,0.0f,0.0f,0.0f);
//            PowerManager.start();
            long tm = 0;

            float a = 0;
            while (opModeIsActive()){
                Robot.Motors.RB.setPower(1);
                Robot.Motors.RF.setPower(1);
                Robot.Motors.LB.setPower(1);
                Robot.Motors.LF.setPower(1);
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
