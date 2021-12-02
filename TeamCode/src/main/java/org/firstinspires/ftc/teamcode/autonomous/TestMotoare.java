package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous(name="TestMotoare", group="Pushbot")

public class TestMotoare extends LinearOpMode {

    boolean rev1b = false;
    boolean rev2b = false;
    boolean yellowb = false;
    boolean miscb = false;

    DcMotorEx yellowJacket;
    DcMotorEx rev;
    DcMotorEx rev2;
    DcMotorEx misc;
    public void onRev1Pressed(){
        rev1b = !rev1b;
        if(rev1b){
            rev.setPower(1);
        }else{
            rev.setPower(0);
        }
    }

    public void onRev2Pressed(){
        rev2b = !rev2b;
        if(rev2b){
            rev2.setPower(1);
        }else{
            rev2.setPower(0);
        }
    }

    public void onYellowPressed(){
        yellowb = !yellowb;
        if(yellowb){
            yellowJacket.setPower(1);
        }else{
            yellowJacket.setPower(0);
        }
    }
    public void onMiscPressed(){
        miscb = !miscb;
        if(miscb){
            misc.setPower(1);
        }else{
            misc.setPower(0);
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        while(opModeIsActive()){
            yellowJacket = hardwareMap.get(DcMotorEx.class,"yellow");
            rev = hardwareMap.get(DcMotorEx.class,"rev");
            rev2 = hardwareMap.get(DcMotorEx.class, "rev2");
            misc = hardwareMap.get(DcMotorEx.class,"misc");
            if(gamepad1.a)
                onRev1Pressed();
            if(gamepad1.b)
                onRev2Pressed();
            if(gamepad1.x)
                onYellowPressed();
            if(gamepad1.y)
                onMiscPressed();

            telemetry.addData("rev1 ", rev.getVelocity(AngleUnit.DEGREES));
            telemetry.addData("rev2 ", rev2.getVelocity(AngleUnit.DEGREES));
            telemetry.addData("y ", yellowJacket.getVelocity(AngleUnit.DEGREES));
            telemetry.addData("m ", misc.getVelocity(AngleUnit.DEGREES));
            telemetry.update();
        }
    }
}
