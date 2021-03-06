package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.lang.reflect.Field;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Robot {

    //thread pool pt executie pe timp/ executie pe conditii
    //cu functii lambda(scary shit i know, o sa le explic
    //la boboci)(lie)
    public static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);


    public static Servo CUPA;

    public static class Motors{
        public static DcMotorEx LF = null; //1 pe poza
        public static DcMotorEx RF = null; //2 pe poza
        public static DcMotorEx LB = null; //3 pe poza
        public static DcMotorEx RB = null; //4 pe poza

        public static DcMotorServo LIFT = null;
        public static DcMotorEx INTAKE = null;
        public static DcMotorEx CARUSEL = null;
    }

    public static class Range{
        public static Rev2mDistanceSensor Front = null;
        public static Rev2mDistanceSensor Left = null;
        public static Rev2mDistanceSensor Back = null;
        public static Rev2mDistanceSensor Right = null;
    }

    public static BNO055IMU Gyroscope = null;

    public static HardwareMap hMap = null;
    public static OpModeAddition opMode = null;



    public static void init(OpModeAddition om, HardwareMap m) {
        Robot.opMode = om;
        Robot.hMap = m;

        Robot.doHardwareMap();
        Robot.doGyroscopeSetup();
        Robot.doMotorsSetup();
        Robot.doInitSystems();

    }

    private static void doMotorsSetup()  {


        Motors.LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motors.LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motors.RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motors.RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        Motors.LIFT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Motors.LB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Motors.LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Motors.RB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Motors.RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        Motors.LIFT.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Motors.LB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Motors.LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Motors.RB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Motors.RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        Motors.LIFT.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Motors.LB.setDirection(DcMotorSimple.Direction.REVERSE);
        Motors.LF.setDirection(DcMotorSimple.Direction.REVERSE);


        //Motors.LIFT = Robot.hMap.get(DcMotorEx.class, "lift_motor");
    }

    private static void doHardwareMap()  {
        //hopefully this wont need explanation



        Motors.LB = Robot.hMap.get(DcMotorEx.class, "motorLB");
        Motors.LF = Robot.hMap.get(DcMotorEx.class, "motorLF");
        Motors.RB = Robot.hMap.get(DcMotorEx.class, "motorRB");
        Motors.RF = Robot.hMap.get(DcMotorEx.class, "motorRF");
      //  Motors.LIFT = Robot.hMap.get(DcMotorEx.class, "lift_motor");

        Robot.Gyroscope = Robot.hMap.get(BNO055IMU.class , "gyro" );

        if(Robot.Gyroscope == null)
            Robot.Gyroscope = Robot.hMap.get(BNO055IMU.class , "imu" );

    }

    private static void doGyroscopeSetup() {
        //some exception handling in case somehow doHardwareMap fucked up


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

    public static void doRangerSetup(){
         try{
            // Range.Front = Robot.hMap.get(Rev2mDistanceSensor.class,"distFront");
             //Range.Left = Robot.hMap.get(Rev2mDistanceSensor.class,"distLeft");
            // Range.Back = Robot.hMap.get(Rev2mDistanceSensor.class,"distBack");
           //  Range.Right = Robot.hMap.get(Rev2mDistanceSensor.class,"distRight");

             //Range.Left.getDeviceClient().setM

         }catch (Exception e){
             //RangerPositioning.healthyRangers = false;
         }
    }

    private static void doInitSystems()  {
        Motors.INTAKE = hMap.get(DcMotorEx.class,"motorI");
        Motors.CARUSEL = hMap.get(DcMotorEx.class,"motorC");
        Motors.LIFT = new DcMotorServo(hMap,"motorL",13.7f,28);
        CUPA = hMap.get(Servo.class, "servo");
    }
    public static float getAngle(){
        Orientation angles = Robot.Gyroscope.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        return (angles.firstAngle + 90) % 360;
    }

    public static float getFrontDistance(){
        return (float)Range.Front.getDistance(DistanceUnit.MM) + 437/2;
    }

    public static float getBackDistance(){
        return (float)Range.Back.getDistance(DistanceUnit.MM) + 437/2;
    }

    public static float getLeftDistance(){
        return (float)Range.Left.getDistance(DistanceUnit.MM) + 335/2;
    }

    public static float getRightDistance(){
        return (float)Range.Right.getDistance(DistanceUnit.MM) + 335/2;
    }


}
