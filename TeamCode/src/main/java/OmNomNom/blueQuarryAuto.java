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
        drive.gateRelease();

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
            lift.clawInit();
            sleep(100);
            if (skystonePos == "1 & 4"){

                //skystone 1
                lift.release();
                drive.strafeLeftInches(.375, 5.5);
                sleep(200);
                drive.moveGyro(.4, 41, 0);
                sleep(250);
                lift.grab();
                drive.moveGyro(-.4, 17, 0);
                sleep(100);
                drive.turn(-90, .45);
                drive.moveGyro(.4, 58, -90);
                lift.release();
                drive.moveGyro(-.6, 10 + 105, -90);
                sleep(500);

                //skystone 2
                drive.moveGyro(.32,9, -90);
                drive.turn(0,.45);
                sleep(100);
                drive.moveGyro(.4, 20, 0);
                lift.grab();
                drive.moveGyro(-.4, 14, 0);
                drive.turn(-90,.45);
                sleep(100);
                drive.moveGyro(.4,  10+ 97, -90);
                lift.release();
                sleep(100);
                drive.moveGyro(-.6,16,-90);
                sleep(250);
                drive.strafeRightInches(.3, 5);

            }
            else if (skystonePos == "2 & 5"){

                //skystone 1
                lift.release();
                sleep(200);
                drive.moveGyro(.4, 42, 0);
                sleep(250);
                lift.grab();
                drive.moveGyro(-.4, 14, 0);
                sleep(100);
                drive.turn(-90, .45);
                drive.moveGyro(.4, 75, -90);
                lift.release();
                drive.moveGyro(-.6, 11 + 102, -90);
                sleep(500);

                //skystone 2
                drive.moveGyro(.32,.1, -90);
                drive.turn(0,.45);
                sleep(100);
                drive.moveGyro(.4, 20, 0);
                lift.srvClaw2.setPosition(.1);
                drive.moveGyro(-.4, 14, 0);
                drive.turn(-90,.45);
                sleep(100);
                drive.moveGyro(.4,  10+ 101, -90);
                lift.release();
                sleep(100);
                drive.moveGyro(-.6,15,-90);
                sleep(250);
                drive.strafeRightInches(.3, 5);


            }
            else {

                //skystone 1
                lift.release();
                drive.strafeRightInches(.35, 7.75);
                sleep(200);
                drive.moveGyro(.4, 40, 0);
                sleep(250);
                lift.grab();
                drive.moveGyro(-.4, 14, 0);
                sleep(100);
                drive.turn(-90, .45);
                drive.moveGyro(.4, 88, -90);
                lift.release();
                drive.moveGyro(-.6, 11 + 105, -90);
                sleep(500);

                //skystone 2
//                drive.moveGyro(.32,6, -90);
                drive.turn(0,.45);
                drive.strafeRightInches(.3,12);
                sleep(250);
                drive.moveGyro(.4, 20, 0);
                lift.srvClaw2.setPosition(.1);
                drive.moveGyro(-.4, 14, 0);
                sleep(200);
                drive.turn(-90,.45);
                sleep(100);
                drive.strafeLeftInches(.3, .75);
                sleep(200);
                drive.moveGyro(.4,  10+ 101, -90);
                lift.release();
                sleep(100);
                drive.moveGyro(-.6,13,-90);
                sleep(250);
                drive.strafeRightInches(.3, 5);


            }

        }
    }

}