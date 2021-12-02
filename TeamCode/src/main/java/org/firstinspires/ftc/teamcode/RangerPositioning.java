package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class RangerPositioning {

    public static boolean healthyRangers = true;
    public static boolean reliable = true;

    public static class Distances{
        public static float R1 = 0;
        public static float R2 = 0;
        public static float R3 = 0;
        public static float R4 = 0;
    }

    public static float Rx = 0;
    public static float Ry = 0;

    public static void updateRanges(){
        if(!healthyRangers){
            return;
        }

        float angle = Robot.getAngle();
        if(angle > 0 && angle <= 90){
            Distances.R1 = (float)Robot.R1.getDistance(DistanceUnit.CM);
            Distances.R2 = (float)Robot.R2.getDistance(DistanceUnit.CM);
            Distances.R3 = (float)Robot.R3.getDistance(DistanceUnit.CM);
            Distances.R4 = (float)Robot.R4.getDistance(DistanceUnit.CM);
        }else if(angle > 90 && angle <= 180){
            Distances.R1 = (float)Robot.R4.getDistance(DistanceUnit.CM);
            Distances.R2 = (float)Robot.R1.getDistance(DistanceUnit.CM);
            Distances.R3 = (float)Robot.R2.getDistance(DistanceUnit.CM);
            Distances.R4 = (float)Robot.R3.getDistance(DistanceUnit.CM);
        }else if(angle > 180 && angle <= 270){
            Distances.R1 = (float)Robot.R3.getDistance(DistanceUnit.CM);
            Distances.R2 = (float)Robot.R4.getDistance(DistanceUnit.CM);
            Distances.R3 = (float)Robot.R1.getDistance(DistanceUnit.CM);
            Distances.R4 = (float)Robot.R2.getDistance(DistanceUnit.CM);
        }else if(angle > 270 && angle <= 360){
            Distances.R1 = (float)Robot.R3.getDistance(DistanceUnit.CM);
            Distances.R2 = (float)Robot.R2.getDistance(DistanceUnit.CM);
            Distances.R3 = (float)Robot.R1.getDistance(DistanceUnit.CM);
            Distances.R4 = (float)Robot.R4.getDistance(DistanceUnit.CM);
        }
    }

    public static float yIntercept(float xR, float xY){
        return Ry - (float)Math.tan(Robot.getAngle())*Rx;
    }
    public static float yIntercept(float xR, float xY,float l){
        return Ry - (float)Math.tan(Robot.getAngle())*(l-Rx);
    }
    public static float xIntercept(float xR, float xY){
        return ((float)Math.tan(Robot.getAngle())*Rx - Ry)/2;
    }
    public static float xIntercept(float xR, float xY,float l){
        return ((float)Math.tan(Robot.getAngle())*Rx + l - Ry)/(float)Math.tan(Robot.getAngle());
    }

    public static void calculate(){
        float m = (float)Math.tan(Math.toRadians(Robot.getAngle()));
        RangerPositioning.updateRanges();

    }

    
}
