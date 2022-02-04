package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Movement;
import org.firstinspires.ftc.teamcode.OmniSimple;
import org.firstinspires.ftc.teamcode.Omnidirectional;
import org.firstinspires.ftc.teamcode.OpModeAddition;
import org.firstinspires.ftc.teamcode.Robot;
@TeleOp(name="ControlDemo", group="Control")

public class ControlDemo extends LinearOpMode implements OpModeAddition {


    @Override
    public void runOpMode() throws InterruptedException {
        Robot.init(this,hardwareMap);
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()){


            //omni
            double[] debug = OmniSimple.calculateAndSet(gamepad1.right_stick_x*80,gamepad1.right_stick_y*80,gamepad1.left_stick_x*3.1415f);


            //leeft
            Robot.Motors.LIFT.setAngle(gamepad2.left_trigger * 2000,0.7f);


            //intec
            if(gamepad2.right_trigger > 0.1 && gamepad2.right_trigger <= 0.5){
                Robot.Motors.INTAKE.setPower(-0.5);
            }else if(gamepad2.right_trigger > 0.5 && gamepad2.right_trigger <= 1){
                Robot.Motors.INTAKE.setPower(-1);
            }else{
                Robot.Motors.INTAKE.setPower(0);
            }

            if(gamepad2.right_bumper)
                Robot.Motors.INTAKE.setPower(1);

            //servou

            Robot.CUPA.setPosition(gamepad2.left_stick_y);



            telemetry.addData("w1",debug[0]);
            telemetry.addData("w2",debug[1]);
            telemetry.addData("w3",debug[2]);
            telemetry.addData("w4",debug[3]);
            telemetry.addData("encoder",Robot.Motors.LIFT.motor.getCurrentPosition());
            telemetry.update();

        }
    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
