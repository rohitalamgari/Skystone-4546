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
        if(!isStopRequested()){
            //goAlternatePark(true);
            if (skystonePos == "1 & 4"){
                drive.armUp();
                sleep(100);
                drive.strafeRight(.3,200);
                drive.moveGyro(.25,38,0);
                sleep(100);
                drive.moveGyro(.25, 7, 0);
                drive.armDown();
                sleep(100);
                drive.moveGyro(-.25,12,0);
                sleep(100);
                drive.turn(-90,.35);
                drive.moveGyro(.35,70,-90);
                drive.moveGyro(-.35,5,-90);
                drive.armUp();
                sleep(100);
                drive.moveGyro(-.35,20,-90);
                sleep(200);
                drive.armDown();
                //drive.turn(0,.3);
            }
            else if (skystonePos == "2 & 5"){
                drive.armUp();
                drive.moveGyro(.25,28,0);
                sleep(100);
                drive.turn(90,.35);
                drive.moveGyro(.25,14,90);
                sleep(100);
                drive.turn(0,0.35);
                drive.moveGyro(.25,13,0);
                drive.armDown();
                sleep(100);
                drive.moveGyro(-.25, 13, 0);
                drive.turn(-90, .35);
                drive.moveGyro(.35,85,-90);
                drive.armUp();
                sleep(100);
                drive.moveGyro(-.35, 25,-90);
                sleep(200);
                drive.armDown();
            }
            else{
                drive.armUp();
                drive.moveGyro(.25,28,0);
                sleep(100);
                drive.turn(90,.35);
                drive.moveGyro(.25,24,90);
                sleep(100);
                drive.turn(0,0.35);
                drive.moveGyro(.25,13,0);
                drive.armDown();
                sleep(100);
                drive.moveGyro(-.25, 13, 0);
                drive.turn(-90, .35);
                drive.moveGyro(.35,95,-90);
                drive.armUp();
                sleep(100);
                drive.moveGyro(-.35, 25,-90);
                sleep(200);
                drive.armDown();
            }

        }
    }
    public void goPark(boolean color){
        if (color){
            while (drive.blueCount() < 200){
                drive.go(0.3);
            }
            stop();
        }
        else{
            while (drive.redCount() < 250) {
                drive.go(0.3);
            }
            stop();
        }
    }
    //true is blue
    public void goAlternatePark(boolean color) throws InterruptedException{
        //while (drive.blueCount() < 200 {
        if (color){
            drive.moveGyro(0.3, 30, 0);
            drive.turn(-90, 0.5);
            goPark(true);
        }
        else {
            drive.moveGyro(0.3, 30, 0);
            drive.turn(90, 0.5);
            goPark(false);
        }
    }

}