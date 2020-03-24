package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;
import Snack.SnackLift;
import Snack.VuforiaBitmap;

@Autonomous(name = "Red Quarry Auto", group = "4546")
public class redQuarryAuto extends LinearOpMode {

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
            telemetry.addData("medX: ", sample.medX(false));
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
                drive.strafeRightInches(.35, 10.5);
                sleep(200);
                drive.moveGyro(.4, 42, 0);
                sleep(250);
                lift.grab();
                drive.moveGyro(-.4, 16, 0);
                sleep(100);
                drive.turn(90, .45);
                sleep(150);
                drive.moveGyro(.4, 59, 90);
                lift.release();
                sleep(100);
                drive.strafeRightInches(.3, .25);
                sleep(200);
                drive.moveGyro(-.6, 10 + 109, 90);
                sleep(500);

                //skystone 2
                drive.moveGyro(.35,11.25, 90);
                sleep(150);
                drive.turn(0,.45);
                sleep(100);
                drive.moveGyro(.4, 20, 0);
                lift.srvClaw1.setPosition(1);
                sleep(200);
                drive.moveGyro(-.4, 14, 0);
                sleep(150);
                drive.turn(90,.45);
                sleep(200);
                drive.strafeRightInches(.3, 2.25);
                sleep(150);
                drive.moveGyro(.4,  10+ 100, 90);
                lift.release();
                sleep(100);
                drive.moveGyro(-.6,19,90);
                sleep(250);
                drive.strafeLeftInches(.3,5);

            }
            else if (skystonePos == "2 & 5"){

                //skystone 1
                lift.release();
                drive.strafeRightInches(.325, .2);
                sleep(200);
                drive.moveGyro(.4, 42, 0);
                sleep(250);
                lift.grab();
                drive.moveGyro(-.4, 14, 0);
                sleep(100);
                drive.turn(90, .45);
                sleep(150);
                drive.moveGyro(.4, 79, 90);
                lift.release();
                sleep(100);
                drive.strafeRightInches(.25, .75);
                sleep(200);
                drive.moveGyro(-.6, 11 + 107, 90);
                sleep(500);

                //skystone 2
                drive.moveGyro(.32,.1, 90);
                sleep(200);
                drive.turn(0,.45);
                sleep(100);
                drive.moveGyro(.4, 20, 0);
                lift.srvClaw1.setPosition(1);
                drive.moveGyro(-.4, 14, 0);
                sleep(100);
                drive.turn(90,.475);
                sleep(100);
                drive.strafeRightInches(.25, 2.7);
                sleep(100);
                drive.moveGyro(.4,  10+ 104, 90);
                lift.release();
                sleep(100);
                drive.moveGyro(-.6,18,90);
                sleep(250);
                drive.strafeLeftInches(.3, 5);
            }
            else{

                //skystone 1
                lift.release();
                drive.strafeLeftInches(.35, 5);
                sleep(200);
                drive.moveGyro(.4, 40, 0);
                sleep(250);
                lift.grab();
                drive.moveGyro(-.4, 14, 0);
                sleep(100);
                drive.turn(90, .45);
                sleep(200);
                drive.moveGyro(.4, 91, 90);
                lift.release();
                sleep(100);
                drive.strafeRightInches(.3, .25);
                sleep(200);
                drive.moveGyro(-.6, 11 + 110, 90);
                sleep(500);

                //skystone 2
//
                drive.turn(0,.45);
                drive.strafeLeftInches(.3,12);
                sleep(250);
                drive.moveGyro(.4, 20, 0);
                lift.srvClaw1.setPosition(1);
                drive.moveGyro(-.4, 14, 0);
                sleep(200);
                drive.turn(90,.45);
                sleep(100);
                drive.strafeRightInches(.35, 3.2);
                sleep(200);
                drive.moveGyro(.4,  10+ 107, 90);
                lift.release();
                sleep(100);
                drive.moveGyro(-.6,19,90);
                sleep(250);
                drive.strafeLeftInches(.3, 5);

            }
        }
    }




}