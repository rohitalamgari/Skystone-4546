package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Snack.SnackDrive;

@Autonomous(name = "Skystone Austo", group = "4546")
public class dummyAuto extends LinearOpMode {
    SnackDrive drive = new SnackDrive();
    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap, telemetry);
        waitForStart();
        while(opModeIsActive()){
            drive.startMotors(-.3, .3);
        }
    }
}
