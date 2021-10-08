package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.lang.reflect.Field;

public class Robot {
    public static class Motors{
        public static DcMotor LF = null; //1 pe poza
        public static DcMotor RF = null; //2 pe poza
        public static DcMotor LB = null; //3 pe poza
        public static DcMotor RB = null; //4 pe poza
    }

    public static BNO055IMU Gyroscope = null;

    public static HardwareMap hMap = null;
    public static OpModeAddition opMode = null;

    public  static void init(OpModeAddition om, HardwareMap m) {
        Robot.opMode = om;
        Robot.hMap = m;

        try{
            Robot.doHardwareMap();
            Robot.doGyroscopeSetup();
            Robot.doMotorsSetup();
        } catch (Exception e) {
            return;
        }
    }

    private static void doMotorsSetup() throws Exception {
        if(Motors.LB == null && Motors.LF == null && Motors.RB == null && Motors.RF == null)
            throw  new Exception("a motor was null in doMotorsSetup()");

        Motors.LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motors.LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motors.RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motors.RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Motors.LB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Motors.LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Motors.RB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Motors.RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Motors.LB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Motors.LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Motors.RB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Motors.RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Motors.LB.setDirection(DcMotorSimple.Direction.REVERSE);
        Motors.LF.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private static void doHardwareMap() throws Exception{
        //hopefully this wont need explanation

        if(Robot.hMap == null)
            throw new Exception("Hardwaremap was null, in doHardwareMap");

        Motors.LB = Robot.hMap.get(DcMotor.class, "motorLB");
        Motors.LF = Robot.hMap.get(DcMotor.class, "motorLF");
        Motors.RB = Robot.hMap.get(DcMotor.class, "motorRB");
        Motors.RF = Robot.hMap.get(DcMotor.class, "motorRF");

        Robot.Gyroscope = Robot.hMap.get(BNO055IMU.class , "gyro" );

        if(Robot.Gyroscope == null)
            Robot.Gyroscope = Robot.hMap.get(BNO055IMU.class , "imu" );

    }

    private static void doGyroscopeSetup() throws Exception{
        //some exception handling in case somehow doHardwareMap fucked up
        if(Robot.Gyroscope == null)
            throw new Exception("Gyroscope was null, did doHardwareMap somehow fail??");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        //parameters (todo play with them)
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.loggingEnabled = false;

        Robot.Gyroscope.initialize(parameters);

        //stop robot execution while gyroscope is not initied
        while (opMode.isOpModeIsActive() && !Robot.Gyroscope.isGyroCalibrated()){}

    }



}
