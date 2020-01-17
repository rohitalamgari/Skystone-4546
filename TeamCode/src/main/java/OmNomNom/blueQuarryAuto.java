package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;
import Snack.VuforiaBitmap;

@Autonomous(name = "Blue Quarry Auto", group = "4546")
public class blueQuarryAuto extends LinearOpMode {

    SnackDrive drive = new SnackDrive();
    String skystonePos = "not found";

    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaBitmap sample = new VuforiaBitmap(this);
        drive.init(hardwareMap, telemetry);
        while (!isStarted()){
            skystonePos = sample.skystonePosition;
            telemetry.addData("medX: ", sample.medX(true));
            telemetry.addData("skystonePos", skystonePos);
            telemetry.update();
            //sleep(1000);
        }
        waitForStart();

        drive.moveGyro(.4, 10, 0);
        drive.armUp();
        sleep(250);
        drive.turn(-90, .3);
        drive.moveGyro(.4, 1.25, -90);

        if(!isStopRequested()){
            //goAlternatePark(true);
            if (skystonePos == "1 & 4"){
                sleep(250);
                drive.turn(-180, .3);
                sleep(250);
                drive.moveGyro(-.4, 17.5, 180);
                drive.armDown();
                sleep(100);
                drive.moveGyro(.4, 11, 180);
                sleep(250);
                drive.turn(90,.45);
                sleep(250);
                drive.moveGyro(-.4, 55, 90);
                drive.armUp();
                sleep(100);
                drive.moveGyro(.4, 19, 90);
                drive.armDown();

            }
            else if (skystonePos == "2 & 5"){
                drive.moveGyro(-.4, 7.5, -90);
                sleep(250);
                drive.turn(-180, .3);
                sleep(250);
                drive.moveGyro(-.4, 17.5, 180);
                drive.armDown();
                sleep(100);
                drive.moveGyro(.4, 13, 180);
                sleep(250);
                drive.turn(90,.45);
                sleep(250);
                drive.moveGyro(-.4, 70, 90);
                drive.armUp();
                sleep(100);
                drive.moveGyro(.4, 19, 90);
                drive.armDown();


            }
            else{
                drive.moveGyro(-.4, 17, -90);
                sleep(250);
                drive.turn(-180, .3);
                sleep(250);
                drive.moveGyro(-.4, 17.5, 180);
                drive.armDown();
                sleep(100);
                drive.moveGyro(.4, 10, 180);
                sleep(250);
                drive.turn(90,.45);
                sleep(250);
                drive.moveGyro(-.4, 80, 90);
                drive.armUp();
                sleep(100);
                drive.moveGyro(.4, 19, 90);
                drive.armDown();

            }

        }
    }

}