package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class OpenCvWebcamExample extends LinearOpMode {

    OpenCvWebcam webcam;

    @Override
    public void runOpMode() throws InterruptedException {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        webcam.setPipeline(new SamplePipeline());
    }

    // !!TERMINA IMPLEMENTAREA
    class SamplePipeline extends OpenCvPipeline {

        boolean viewportPaused;

        @Override
        public Mat processFrame(Mat input) {
            return null; // implementeaza Mat frame ul pt ce vrei sa detectezi
        }

        @Override
        public void onViewportTapped() {

            viewportPaused = !viewportPaused;

            if (viewportPaused) {

                webcam.pauseViewport();
            } else {

                webcam.resumeViewport();
            }
        }
    }
}
