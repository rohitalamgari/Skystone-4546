package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Snack.SnackDrive;

@Autonomous(name = "TestAuto", group = "4546")
public class dummyAuto extends LinearOpMode {
    SnackDrive drive = new SnackDrive();
    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap, telemetry);
        waitForStart();
        if(opModeIsActive()){
            drive.moveGyro(.3,10,0);
            drive.turn(90,.3);
        }
    }
}
