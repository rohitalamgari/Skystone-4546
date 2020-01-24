package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;
import Snack.SnackLift;
import Snack.VuforiaBitmap;

@Autonomous(name = "Blue Quarry Auto", group = "4546")
public class blueQuarryAuto extends LinearOpMode {

    SnackDrive drive = new SnackDrive();
    SnackLift lift = new SnackLift();
    String skystonePos = "not found";

    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaBitmap sample = new VuforiaBitmap(this);
        drive.init(hardwareMap, telemetry);
        lift.init(hardwareMap, telemetry);
        while (!isStarted()){
            skystonePos = sample.skystonePosition;
            telemetry.addData("medX: ", sample.medX(true));
            telemetry.addData("skystonePos", skystonePos);
            telemetry.update();
            sleep(1000);
        }
        waitForStart();



        if(!isStopRequested()){
            //goAlternatePark(true);
            if (skystonePos == "1 & 4"){

                //skystone 1
                lift.release();
                drive.strafeLeftInches(.35, 2);
                sleep(200);
                drive.moveGyro(.4, 42, 0);
                sleep(250);
                lift.grab();
                drive.moveGyro(-.4, 14, 0);
                sleep(100);
                drive.turn(-90, .35);
                drive.moveGyro(.4, 52, -90);
                lift.release();
                drive.moveGyro(-.6, 10 + 102, -90);
                sleep(500);

                //skystone 2
                drive.moveGyro(.32,7.5, -90);
                drive.turn(0,.35);
                sleep(100);
                drive.moveGyro(.4, 20, 0);
                lift.grab();
                drive.moveGyro(-.4, 14, 0);
                drive.turn(-90,.35);
                sleep(100);
                drive.moveGyro(.4,  10+ 94, -90);
                lift.release();
                sleep(100);
                drive.moveGyro(-.6,13,-90);

            }
            else if (skystonePos == "2 & 5"){

                //skystone 1
                lift.release();
                drive.strafeRightInches(.35, 1);
                sleep(200);
                drive.moveGyro(.4, 42, 0);
                sleep(250);
                lift.grab();
                drive.moveGyro(-.4, 14, 0);
                sleep(100);
                drive.turn(-90, .35);
                drive.moveGyro(.4, 72, -90);
                lift.release();
                drive.moveGyro(-.6, 11 + 100, -90);
                sleep(500);

                //skystone 2
                drive.moveGyro(.32,.1, -90);
                drive.turn(0,.35);
                sleep(100);
                drive.moveGyro(.4, 20, 0);
                lift.grab();
                drive.moveGyro(-.4, 14, 0);
                drive.turn(-90,.35);
                sleep(100);
                drive.moveGyro(.4,  10+ 98, -90);
                lift.release();
                sleep(100);
                drive.moveGyro(-.6,12,-90);


            }
            else {

                //skystone 1
                lift.release();
                drive.strafeRightInches(.35, 6);
                sleep(200);
                drive.moveGyro(.4, 42, 0);
                sleep(250);
                lift.grab();
                drive.moveGyro(-.4, 14, 0);
                sleep(100);
                drive.turn(-90, .35);
                drive.moveGyro(.4, 82, -90);
                lift.release();
                drive.moveGyro(-.6, 11 + 102, -90);

                //skystone 2
//                drive.moveGyro(.32,6, -90);
                drive.turn(0,.35);
                drive.strafeRightInches(.3,3.5);
                sleep(100);
                drive.moveGyro(.4, 20, 0);
                lift.grab();
                drive.moveGyro(-.4, 14, 0);
                drive.turn(-90,.35);
                sleep(100);
                drive.moveGyro(.4,  10+ 98, -90);
                lift.release();
                sleep(100);
                drive.moveGyro(-.6,10,-90);


            }

        }
    }

}