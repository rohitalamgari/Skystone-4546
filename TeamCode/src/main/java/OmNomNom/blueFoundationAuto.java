package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;

@Autonomous(name = "park Auto", group = "4546")
public class blueFoundationAuto extends LinearOpMode{
    SnackDrive drive = new SnackDrive();

    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap, telemetry);
        waitForStart();
        if (!isStopRequested()){
            drive.moveGyro(1,30,180);
        }

    }
}
