package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.DcMotorServo;
import org.firstinspires.ftc.teamcode.OpModeAddition;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name="Test Lift", group="tests")
public class TestLift extends LinearOpMode implements OpModeAddition {

        @Override
        public void runOpMode() throws InterruptedException {

//        DcMotorServo lift_motor = new DcMotorServo(hardwareMap, "lift_motor", 53, 1484);

            DcMotorEx lift_motor = hardwareMap.get(DcMotorEx.class, "lift_motor");
            lift_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            ElapsedTime runtime = new ElapsedTime();

            int position = 1;

            waitForStart();

//            Robot.init(this, hardwareMap);
//            Robot.Motors.LIFT.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            while (isOpModeIsActive())
            {
//                Robot.Motors.LIFT.setVelocity(200);

                if (gamepad1.right_bumper)
                    position++;
                else if (gamepad1.left_bumper)
                    position--;

                //Robot.Motors.LIFT.setPower(1);

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

//                lift_motor.setAngle(1, 0.2f);

                telemetry.addData("current pos: ", lift_motor.getCurrentPosition());
                telemetry.addData("position: ", position);

                telemetry.update();
        }
    }

    @Override
    public boolean isOpModeIsActive() {
        return opModeIsActive();
    }
}
