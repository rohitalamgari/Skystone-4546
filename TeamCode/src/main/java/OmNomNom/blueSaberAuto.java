package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Snack.SnackDrive;
import Snack.SnackLift;

@Autonomous(name = "BlueSaber", group = "4546")
public class blueSaberAuto extends LinearOpMode {
    SnackDrive drive = new SnackDrive();
    SnackLift lift = new SnackLift();
    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap, telemetry);
        lift.init(hardwareMap, telemetry);
        lift.clawInit();
        waitForStart();
        if (!isStopRequested()){
            lift.release();
            sleep(100);
            //Moves and strafes towards foundation
            drive.moveGyro(0.3,6,0);
            sleep(250);
            drive.strafeLeftInches(.4, 10);
            //moves up and grabs it
            sleep(250);
            drive.moveGyro(0.3,28,0);
            sleep(100);
            drive.moveGyro(.3, 5, 0);
            sleep(300);
            drive.foundationDown();
            //comes back and turns
            sleep(750);
            drive.moveGyro(-0.3,55,0);
            drive.foundationUp();
            sleep(10000);
            drive.strafeRightInches(.5, 30);
            lift.grab();
            drive.strafeRightInches(.5,45);

        }
    }
}

