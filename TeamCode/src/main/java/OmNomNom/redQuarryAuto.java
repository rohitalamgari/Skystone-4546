package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;
import Snack.VuforiaBitmap;

@Autonomous(name = "Red Quarry Auto", group = "4546")
public class redQuarryAuto extends LinearOpMode {

    SnackDrive drive = new SnackDrive();
    String skystonePos = "not found";

    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaBitmap sample = new VuforiaBitmap(this);
        drive.init(hardwareMap, telemetry);
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
            if (skystonePos == "1 & 4"){
                drive.armUp();
                sleep(100);
                drive.strafeLeft(.3,200);
                drive.moveGyro(.25,38,0);
                sleep(100);
                drive.moveGyro(.25, 6, 0);
                drive.armDown();
                sleep(100);
                drive.moveGyro(-.25,10,0);
                sleep(100);
                drive.turn(90,.35);
                drive.moveGyro(.35,70,90);
                drive.moveGyro(-.35,5,90);
                drive.armUp();
                sleep(100);
                drive.moveGyro(-.35,15,90);
                //drive.turn(0,.3);
            }
            else if (skystonePos == "2 & 5"){
                drive.armUp();
                drive.moveGyro(.25,28,0);
                sleep(100);
                drive.turn(-90,.35);
                drive.moveGyro(.25,14,-90);
                sleep(100);
                drive.turn(0,0.35);
                drive.moveGyro(.25,11,0);
                drive.armDown();
                sleep(100);
                drive.moveGyro(-.25, 10, 0);
                drive.turn(90, .35);
                drive.moveGyro(.35,85,90);
                drive.armUp();
                sleep(100);
                drive.moveGyro(-.35, 20,90);
            }
            else{
                drive.armUp();
                drive.moveGyro(.25,28,0);
                sleep(100);
                drive.turn(-90,.35);
                drive.moveGyro(.25,25,-90);
                sleep(100);
                drive.turn(0,0.35);
                drive.moveGyro(.25,11,0);
                drive.armDown();
                sleep(100);
                drive.moveGyro(-.25, 10, 0);
                drive.turn(90, .35);
                drive.moveGyro(.35,95,90);
                drive.armUp();
                sleep(100);
                drive.moveGyro(-.35, 20,90);
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