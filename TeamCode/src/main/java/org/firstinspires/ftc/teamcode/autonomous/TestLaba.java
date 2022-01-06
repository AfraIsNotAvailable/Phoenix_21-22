package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
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
@Autonomous(name="TestLaba", group="Pushbot")

public class TestLaba extends LinearOpMode implements OpModeAddition{

    @Override
    public void runOpMode()  {

        CRServo servo = hardwareMap.get(CRServo.class,"servo");

        waitForStart();

        while (isOpModeIsActive()){
            if(gamepad1.a)
                servo.setPower(1);
            else
                servo.setPower(0);
        }


    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
