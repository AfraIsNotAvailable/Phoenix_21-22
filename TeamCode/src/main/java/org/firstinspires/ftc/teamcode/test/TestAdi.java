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

@TeleOp(name="Test Adi", group="tests")
public class TestAdi extends LinearOpMode implements OpModeAddition {

    @Override
    public void runOpMode() throws InterruptedException {
       // Robot.init(this,hardwareMap);
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class,"motor");
        DcMotorEx motor2 = hardwareMap.get(DcMotorEx.class,"motor2");
        waitForStart();

        while (opModeIsActive()){

            if(gamepad1.a){
                motor.setPower(-1);
                motor2.setPower(-1);
            }else{
                motor.setPower(0);
                motor2 .setPower(0);
            }


            telemetry.update();

        }
    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
