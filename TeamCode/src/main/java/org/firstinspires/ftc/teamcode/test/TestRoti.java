package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="Test Roti Ad", group="tests")
public class TestRoti extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotorEx motor_fata = null;
        DcMotorEx motor_spate = null;

        waitForStart();

        motor_fata = hardwareMap.get(DcMotorEx.class, "motor_fata");
        motor_spate = hardwareMap.get(DcMotorEx.class, "motor_spate");

        motor_fata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor_spate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (opModeIsActive()) {

            motor_fata.setPower(gamepad1.right_stick_x);
            motor_spate.setPower(gamepad1.left_stick_x);

            telemetry.addData("Motor 1: ", motor_fata.getPower());
            telemetry.addData("Motor 2: ", motor_spate.getPower());
            telemetry.update();
        }


    }
}
