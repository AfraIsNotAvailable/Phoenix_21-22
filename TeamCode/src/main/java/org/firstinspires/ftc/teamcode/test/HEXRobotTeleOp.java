package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utils.GamepadEvents;

@TeleOp(name="Non-Hardware Tests: Sound / GamepadEvents ~")
public class HEXRobotTeleOp extends LinearOpMode {

    GamepadEvents player1 = null;

    boolean button = false;
    boolean goldFound;
    boolean silverFound;
    boolean oof_found;

    boolean is_square = false;    // Gamepad button state variables
    boolean is_circle = false;
    boolean is_cross = false;
    boolean is_triangle = false;

    boolean was_square = false;   // Gamepad button history variables
    boolean was_circle = false;
    boolean was_cross = false;
    boolean was_triangle = false;

    @Override
    public void runOpMode() throws InterruptedException {

        player1 = new GamepadEvents(gamepad1);

        int silverSoundID = hardwareMap.appContext.getResources().getIdentifier("silver", "raw", hardwareMap.appContext.getPackageName());
        int goldSoundID = hardwareMap.appContext.getResources().getIdentifier("gold", "raw", hardwareMap.appContext.getPackageName());
        int oofSoundID = hardwareMap.appContext.getResources().getIdentifier("oof", "raw", hardwareMap.appContext.getPackageName());

        if (goldSoundID != 0)
            goldFound = SoundPlayer.getInstance().preload(hardwareMap.appContext, goldSoundID);

        if (silverSoundID != 0)
            silverFound = SoundPlayer.getInstance().preload(hardwareMap.appContext, silverSoundID);

        if (oofSoundID != 0)
            oof_found = SoundPlayer.getInstance().preload(hardwareMap.appContext, oofSoundID);


        telemetry.addData("gold resource", goldFound ? "Found" : "NOT found\n Add gold.wav to /src/main/res/raw");
        telemetry.addData("silver resource", silverFound ? "Found" : "Not found\n Add silver.wav to /src/main/res/raw");
        telemetry.addData("oof resource", oof_found ? "Found" : "Not found\n Add oof.wav to /src/main/res/raw");
        telemetry.addData("Press Start", "Waiting");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            if ((is_cross = gamepad1.cross) && !was_cross) {

                button = !button;
            }

            if (silverFound && (is_square = gamepad1.square) && !was_square) {
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, silverSoundID);
                telemetry.addData("Playing", "Resource Silver");
                telemetry.update();
            }

            if (goldFound && (is_circle = gamepad1.circle) && !was_circle) {
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, goldSoundID);
                telemetry.addData("Playing", "Resource Gold");
                telemetry.update();
            }

            if (oof_found && (is_triangle = gamepad1.triangle) && !was_triangle) {
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, oofSoundID);
                telemetry.addData("Playing", "Resource Oof");
                telemetry.update();
            }

            was_cross = is_cross;
            was_circle = is_circle;
            was_square = is_square;
            was_triangle = is_triangle;

            telemetry.addData("Press button CROSS to do smtn", button);
            telemetry.addData("Press CIRCLE to start playing silver sound", "");
            telemetry.addData("Press SQUARE to start playing gold sound", "");
            telemetry.addData("Press TRIANGLE to OOF", "");
            telemetry.update();
        }

    }
}