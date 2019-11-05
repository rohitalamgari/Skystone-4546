package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;

@Autonomous(name = "Red Foundation Auto", group = "4546")
public class redFoundationAuto extends LinearOpMode{
    SnackDrive drive = new SnackDrive();

    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap, telemetry);
        waitForStart();
        if (!isStopRequested()){
            drive.moveGyro(0.3,12,0);
            //drive.strafeLeft(0.3,250);
        }

    }
}
