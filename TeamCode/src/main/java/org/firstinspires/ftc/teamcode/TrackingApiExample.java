package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvTrackerApiPipeline;

@TeleOp(name = "OpenCV Tracking Test", group = "OpenCV")
//@Disabled
public class TrackingApiExample extends LinearOpMode {

    OpenCvCamera phoneCam;
    OpenCvTrackerApiPipeline trackerApiPipeline;

    @Override
    public void runOpMode() throws InterruptedException {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        trackerApiPipeline = new OpenCvTrackerApiPipeline();
        phoneCam.setPipeline(trackerApiPipeline);

        /*phoneCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener(){

            @Override
            public void onOpened(){

                phoneCam.startStreaming(720, 480, OpenCvCameraRotation.UPRIGHT);
            }

        });*/

    }
}
