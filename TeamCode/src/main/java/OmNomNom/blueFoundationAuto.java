package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import Snack.SnackDrive;
import Snack.SnackLift;

@Autonomous(name = "Blue Foundation Auto", group = "4546")
public class blueFoundationAuto extends LinearOpMode {
    SnackDrive drive = new SnackDrive();
    SnackLift lift = new SnackLift();
    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap, telemetry);
        lift.init(hardwareMap, telemetry);
        waitForStart();
        if (!isStopRequested()){
            //lift.clawInit();
            sleep(100);
            //Moves and strafes towards foundation
            drive.moveGyro(0.3,6,0);
            sleep(250);
            drive.strafeLeftInches(.4, 8.24);
            //moves up and grabs it
            sleep(250);
            drive.moveGyro(0.3,28,0);
            sleep(100);
            drive.moveGyro(.3, 8, 0);
            sleep(300);
            drive.foundationDown();
            //comes back and turns
            sleep(750);
            drive.moveGyro(-0.3,30,0);
            sleep(250);
            drive.turnK(-45,.6);
            sleep(250);
            drive.moveGyro(-.3, 20, -45);
            sleep(250);
            drive.turnK(-90, .6);
            sleep(250);
            drive.foundationUp();
            drive.moveGyro(-.5, 2.5, -90);
            sleep(250);
            drive.moveGyro(.5, 18, -90);

            sleep(250);
            //goes and parks
            drive.strafeLeftInches(.35, 20);
            sleep(250);
            drive.strafeRightInches(.35, 3);
            sleep(100);
            drive.moveGyro(-.3, 40, -90);
            //drive.foundationDown();
        }
    }
}