package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;
import Snack.SnackLift;

@Autonomous(name = "Red Foundation Auto", group = "4546")
public class redFoundationAuto extends LinearOpMode{
    SnackDrive drive = new SnackDrive();
    SnackLift lift = new SnackLift();
    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap, telemetry);
        lift.init(hardwareMap, telemetry);
        waitForStart();
        if (!isStopRequested()){
            drive.moveGyro(0.3,6,0);
            sleep(250);
            drive.strafeRightInches(.4, 2.8);
            sleep(250);
            sleep(250);
            drive.moveGyro(0.3,28,0);
            sleep(100);
            drive.moveGyro(.2, 5, 0);
            sleep(1000);
            lift.grab();
            sleep(500);
            drive.moveGyro(-0.3,50,0);
            lift.release();
            drive.strafeLeftInches(.4, 22);
            sleep(500);
            drive.moveGyro(.3, 8, 0);
            sleep(500);
            drive.strafeGyro(.3,5, 0);
            sleep(250);
            drive.moveGyro(.3, 3, 0);
            sleep(250);
            drive.turn(90,.3);
            sleep(250);
            drive.moveGyro(-.5, 30, 90);

            //drive.foundationDown();

        }

    }
}
