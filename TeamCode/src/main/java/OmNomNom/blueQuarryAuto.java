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
                //skystone 1
                drive.armUp();
                drive.moveGyro(.5, 8, 0);
                sleep(500);
                drive.strafeGyro(.5, 1, 0);
                drive.moveGyro(.5, 23, 0);
                drive.armDown();
                drive.moveGyro(-.5, 8, 0);
                sleep(500);
                drive.turn(0, .25);
                drive.strafeGyro(-.5, 48, 0);
                sleep(500);
                drive.turn(0, .25);
                drive.armUp();
                sleep(300);
                drive.moveGyro(.5, 6, 0);
                drive.moveGyro(-.5, 11, 0);
                drive.armDown();
                sleep(500);
                drive.turn(0, .25);
                drive.strafeGyro(.5, 11, 0);


                //skystone 2
//                drive.strafeGyro(.5, 30, 0);
//                drive.armUp();
//                sleep(500);
//                drive.moveGyro(.5, 6, 0);
//                drive.armDown();
//                drive.moveGyro(-.5, 6, 0);
//                sleep(500);
//                drive.strafeGyro(-.5, 40, 0);
//                drive.armUp();
//                sleep(500);
            }
            else if (skystonePos == "2 & 5"){
                //skystone 1
                drive.armUp();
                drive.moveGyro(.5, 8, 0);
                sleep(500);
                drive.strafeGyro(.5, 10, 0);
                drive.moveGyro(.5, 12, 0);
                drive.armDown();
                drive.moveGyro(-.5, 6, 0);
                sleep(500);
                drive.strafeGyro(-.5, 30, 0);
                drive.armUp();
                sleep(500);
                drive.moveGyro(.5, 5, 0);
                drive.moveGyro(-.5, 5, 0);
                drive.armDown();
                sleep(500);
            }
            else{
                //skystone 1
                drive.armUp();
                drive.moveGyro(.5, 8, 0);
                sleep(500);
                drive.strafeGyro(.5, 18, 0);
                drive.moveGyro(.5, 12, 0);
                drive.armDown();
                drive.moveGyro(-.5, 6, 0);
                sleep(500);
                drive.strafeGyro(-.5, 40, 0);
                drive.armUp();
                sleep(500);
                drive.moveGyro(.5, 5, 0);
                drive.moveGyro(-.5, 5, 0);
                drive.armDown();
                sleep(500);

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