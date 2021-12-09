package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.propkey.qual.PropertyKeyBottom;
import org.firstinspires.ftc.teamcode.OpModeAddition;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name="ControlMoto", group="Control")
public class ControlMoto extends LinearOpMode implements OpModeAddition {

    public double speed;
    public double direction;
    public double right;
    public double left;

    @Override
    public void runOpMode() throws InterruptedException {

        Robot.init(this, hardwareMap);

        waitForStart();
        ElapsedTime runtime = new ElapsedTime();

        while(opModeIsActive()) {

            speed = gamepad1.right_trigger - gamepad1.left_trigger;
            direction = gamepad1.right_stick_x;

            right = speed - direction;
            left = speed + direction;

            if (right > 1)
                right = 1;
            if (right < -1)
                right = -1;
            if (left > 1)
                left = 1;
            if (left < -1)
                left = -1;

            if (gamepad1.right_stick_x != 0) {

                Robot.Motors.LB.setPower(-gamepad1.right_stick_x);
                Robot.Motors.LF.setPower(gamepad1.right_stick_x);
                Robot.Motors.RB.setPower(gamepad1.right_stick_x);
                Robot.Motors.RF.setPower(-gamepad1.right_stick_x);
            }
            else {

                Robot.Motors.LB.setPower(left);
                Robot.Motors.LF.setPower(left);
                Robot.Motors.RB.setPower(right);
                Robot.Motors.RF.setPower(right);
            }
        }
    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
