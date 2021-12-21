package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

public class RangerPositioning {

    public static class Distances{
        public static float R1 = 0;
        public static float R2 = 0;
        public static float R3 = 0;
        public static float R4 = 0;
    }

    public static float terrainSize = 3600;

    public static Vector2D getPosition(){
        float angle = Robot.getAngle();
        if((355 < angle && angle < 360) || (0 < angle && angle < 5)){
            Distances.R1 = (float)Robot.getFrontDistance();
            Distances.R2 = (float)Robot.getLeftDistance();
            Distances.R3 = (float)Robot.getBackDistance();
            Distances.R4 = (float)Robot.getRightDistance();
        }else if(angle >= 85 && angle < 95){
            Distances.R1 = (float)Robot.getLeftDistance();
            Distances.R2 = (float)Robot.getBackDistance();
            Distances.R3 = (float)Robot.getRightDistance();
            Distances.R4 = (float)Robot.getFrontDistance();
        }else if(angle >= 175 && angle < 185){
            Distances.R1 = (float)Robot.getLeftDistance();
            Distances.R2 = (float)Robot.getBackDistance();
            Distances.R3 = (float)Robot.getRightDistance();
            Distances.R4 = (float)Robot.getFrontDistance();
        }else if(angle >= 256 && angle < 275){
            Distances.R1 = (float)Robot.getLeftDistance();
            Distances.R2 = (float)Robot.getBackDistance();
            Distances.R3 = (float)Robot.getRightDistance();
            Distances.R4 = (float)Robot.getFrontDistance();
        }else{
            return new Vector2D(-1f,-1f);
        }
        float x = -1;
        float y = -1;
        if(0 < Distances.R3 && Distances.R3 > 2000f){
            y = terrainSize - (Distances.R1 > 2000 ? 0 : Distances.R1);
        }else{
            y = Distances.R3;
        }
        if(0 < Distances.R2 || Distances.R2 > 2000f){
            x = terrainSize - (Distances.R4 > 2000 ? 0 : Distances.R4);
        }else {
            x = Distances.R2;
        }
        return new Vector2D(x > 0 && x < terrainSize ? x : 0,y > 0 && y < terrainSize ? y : 0);
    }
}
