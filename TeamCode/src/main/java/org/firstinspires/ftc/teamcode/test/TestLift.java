package org.firstinspires.ftc.teamcode.test;

import static java.lang.StrictMath.abs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.DcMotorServo;
import org.firstinspires.ftc.teamcode.OpModeAddition;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name="Test Lift ~", group="tests")
public class TestLift extends LinearOpMode implements OpModeAddition {

    DcMotorEx lift_motor = null;

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotorServo lift_motor = new DcMotorServo(hardwareMap, "lift_motor", 53, 28);
        waitForStart();
        while(opModeIsActive()) {
            lift_motor.setAngle(100 * (gamepad1.left_trigger - gamepad1.right_trigger), 0.2f);
            telemetry.addData("pula",lift_motor.motor.getCurrentPosition());
            //telemetry.addData("pizda",lift_motor.);
        }

        //        lift_motor = hardwareMap.get(DcMotorEx.class, "lift_motor");
//
//        lift_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        lift_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        telemetry.setAutoClear(false);
//        Telemetry.Item lift_pos = telemetry.addData("Lift Position ", lift_motor.getCurrentPosition());
//
//        ElapsedTime runtime = new ElapsedTime();
//
//        while (!isStarted()){
//            lift_pos.setValue(lift_motor.getCurrentPosition());
//            telemetry.update();
//        }
//
//        waitForStart();
//
//        int lift_target = 0;
//        double lift_speed = 0;
//        String lift_current_dir = "up";
//
////            Robot.init(this, hardwareMap);
////            Robot.Motors.LIFT.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        while (isOpModeIsActive())
//        {
//            if (gamepad1.right_bumper){
//
//                lift_target = 200;
//                lift_speed = .5;
//                lift_current_dir = "up";
//
//                lift_motor.setPower(lift_speed);
//                lift_motor.setTargetPosition(lift_target);
//
//                lift_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            }
//            else if (gamepad1.left_bumper){
//
//                lift_target = 0;
//                lift_speed = -0.1;
//                lift_current_dir = "down";
//
//                lift_motor.setPower(lift_speed);
//                lift_motor.setTargetPosition(lift_target);
//
//                lift_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            }
//
//            if (lift_current_dir.equals("down") && (abs(lift_motor.getTargetPosition()) < 5)){
//
//                lift_speed = 0;
//                lift_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            }
//
//            idle();
//
//            if (lift_motor.isBusy()){
//
//                lift_pos.setValue(lift_motor.getCurrentPosition());
//                telemetry.update();
//            }

//                Robot.Motors.LIFT.setVelocity(200);

            /*if (gamepad1.right_bumper)
                position++;
            else if (gamepad1.left_bumper)
                position--;

            if (position >= 3) position = 3;
            else if (position <= 1) position = 1;

            switch (position){
                case 1:
                    lift_motor.setTargetPosition(10);
                    lift_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift_motor.setVelocity(10);
                    break;
                case 2:
                    lift_motor.setTargetPosition(20);
                    lift_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift_motor.setVelocity(10);
                    break;
                case 3:
                    lift_motor.setTargetPosition(30);
                    lift_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift_motor.setVelocity(10);
                    break;
            }

            while (lift_motor.getTargetPosition() - lift_motor.getCurrentPosition() > 10){
                telemetry.addData("Status", "Waiting for motor so reach its target");
                telemetry.update();
            }

            lift_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//                lift_motor.setAngle(1, 0.2f);*/

//            telemetry.addData("current pos: ", lift_motor.getCurrentPosition());
//            telemetry.addData("position: ", position);

//            telemetry.update();
//    }
}

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
