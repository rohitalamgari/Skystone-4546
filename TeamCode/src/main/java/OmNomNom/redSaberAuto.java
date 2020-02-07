package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Snack.SnackDrive;
import Snack.SnackLift;

@Autonomous(name = "RedSaber", group = "4546")
public class redSaberAuto extends LinearOpMode {
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
            drive.strafeRightInches(.4, 14);
            //moves up and grabs it
            sleep(250);
            drive.moveGyro(0.3,28,0);
            sleep(100);
            drive.moveGyro(.3, 5, 0);
            sleep(300);
            drive.foundationDown();
            //comes back and turns
            sleep(750);
            drive.moveGyro(-0.45,55,0);
            drive.foundationUp();
            sleep(5000);
            drive.strafeLeftInches(.5, 40);
            lift.clawInit();
            sleep(250);
            drive.moveGyro(.35, 16, 0);
            sleep(200);
            drive.turn(90, .35);
            sleep(250);
            drive.moveGyro(.25, 19, 90);
            sleep(200);
            drive.strafeRightInches(.35, 9);
            sleep(200);
            drive.moveGyro(-.35, 29, 90);
            sleep(250);
            drive.strafeRightInches(.35, 10);

        }
    }
}
