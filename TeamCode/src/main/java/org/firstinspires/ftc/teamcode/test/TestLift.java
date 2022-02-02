package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.DcMotorServo;
import org.firstinspires.ftc.teamcode.OpModeAddition;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name="Test Lift", group="tests")
public class TestLift extends LinearOpMode implements OpModeAddition {

        @Override
        public void runOpMode() throws InterruptedException {

        DcMotorServo lift_motor = new DcMotorServo(hardwareMap, "lift_motor", 13.7f, 28);
        Servo servo = hardwareMap.get(Servo.class,"servo");

        waitForStart();
        float angle = 0;
        while (opModeIsActive()){
            angle += 3*(gamepad1.right_trigger - gamepad1.left_trigger);
            lift_motor.setAngle( gamepad1.right_trigger * -2000.f ,0.4f);
            telemetry.addData("power",lift_motor.motor.getCurrentPosition());
            telemetry.addData("d",angle);
            telemetry.update();

            servo.setPosition(2* Math.abs(gamepad1.left_stick_x) - 1);
        }

    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
