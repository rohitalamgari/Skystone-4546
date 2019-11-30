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
            drive.moveGyro(-0.3,6,0);
            sleep(250);
            drive.strafeLeft(0.25,1000);
            sleep(250);
            drive.turn(0,0.3);
            sleep(250);
            drive.moveGyro(-0.3,26,0);
            sleep(1000);
            //drive.foundationDown();
            sleep(1000);
            drive.moveGyro(0.225,34,0);
            sleep(500);
            drive.turn(90,1);
            //sleep(500);
            //drive.foundationUp();
            sleep(250);
            drive.moveGyro(0.3,48,90);
        }

    }
}
