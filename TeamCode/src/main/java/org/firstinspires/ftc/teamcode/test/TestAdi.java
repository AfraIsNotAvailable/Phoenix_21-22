package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.DcMotorServo;
import org.firstinspires.ftc.teamcode.Movement;
import org.firstinspires.ftc.teamcode.OpModeAddition;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name="Test Adi", group="test")
public class TestAdi extends LinearOpMode implements OpModeAddition {

    @Override
    public void runOpMode() throws InterruptedException {
        // Robot.init(this,hardwareMap);
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class,"motor");
        DcMotorEx motor2 = hardwareMap.get(DcMotorEx.class,"motor2");
        waitForStart();

        int power = -1;
        boolean pressedLeft = false;
        boolean pressedRight = false;

        while (opModeIsActive()){

            if(gamepad1.left_bumper && !pressedLeft){
                if(power < 0)
                    power += 0.1;
                pressedLeft = true;
            }

            if(!gamepad1.left_bumper && pressedLeft)
                pressedLeft = false;

            if(gamepad1.right_bumper && !pressedRight){
                if(power > -1)
                    power -= 0.1;
                pressedRight = true;
            }

            if(!gamepad1.right_bumper && pressedRight)
                pressedLeft = false;

            if(gamepad1.a){
                motor.setPower(power);
                motor2 .setPower(power);
            }else{
                motor.setPower(0);
                motor2 .setPower(0);
            }
//            motor.setPower(-gamepad1.right_trigger);
//            motor2.setPower(-gamepad1.right_trigger);
            telemetry.addData("Power", power);
            telemetry.addData("left bumper", gamepad1.left_bumper);
            telemetry.addData("right bumper", gamepad1.right_bumper);
            telemetry.addData("pressed left", pressedLeft);
            telemetry.addData("pressed right", pressedRight);
            telemetry.update();

        }
    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}