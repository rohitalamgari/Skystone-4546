package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;

@Autonomous(name = "Park Auto", group = "4546")
public class ParkAuto extends LinearOpMode{
    SnackDrive drive = new SnackDrive();

    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap, telemetry);
        waitForStart();
        if (!isStopRequested()){
            drive.turnK(-45,.3);
            sleep(100);
            drive.turnK(-90,.3);
        }

    }
}
