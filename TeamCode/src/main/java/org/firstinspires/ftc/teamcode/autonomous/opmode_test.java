package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.OpModeAddition;
import org.firstinspires.ftc.teamcode.RangerPositioning;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name="test_range",group ="TEST" )
public class opmode_test extends LinearOpMode implements OpModeAddition {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot.init(this, hardwareMap);
        waitForStart();
        while(opModeIsActive())
        {
            telemetry.addData("distFront: ",Robot.getFrontDistance());
            telemetry.addData("distLeft: ",Robot.getLeftDistance());
            telemetry.addData("distBack: ",Robot.getBackDistance());
            telemetry.addData("distRight: ",Robot.getRightDistance());

            telemetry.addData("position ",String.format("x: %f , y: %f", RangerPositioning.getPosition().x,RangerPositioning.getPosition().y));

            telemetry.update();
        }
    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
