package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Test the accuracy of the triggers/sticks ~", group="tests")
public class TestTriggerStickAccuracy extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Right trigger: \n", Math.floor(gamepad1.right_trigger * 1000) / 1000);
            telemetry.addData("Left trigger: \n", Math.floor(gamepad1.left_trigger * 1000) / 1000);
            telemetry.addData("Right Stick X: \n", Math.floor(gamepad1.right_stick_x * 1000) / 1000);
            telemetry.addData("Right Stick Y: \n", Math.floor(gamepad1.right_stick_y * 1000) / 1000);
            telemetry.addData("Left Stick X: \n", Math.floor(gamepad1.left_stick_x * 1000) / 1000);
            telemetry.addData("Left Stick Y: \n", Math.floor(gamepad1.left_stick_y * 1000) / 1000);
            telemetry.update();
        }
    }
}
