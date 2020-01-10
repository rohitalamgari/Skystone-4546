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

        if(!isStopRequested()){
            //goAlternatePark(true);
            if (skystonePos == "1 & 4"){
                drive.moveGyro(-.4, 2.5, -90);
                sleep(250);
                drive.turn(-180, .3);
                sleep(250);
                drive.moveGyro(-.4, 15, -180);
                drive.armDown();
                sleep(100);
                drive.moveGyro(.4, 6, -180);
                sleep(250);
                drive.turn(90,.3);
                sleep(250);
                drive.moveGyro(-.4, 55, 90);
                drive.armUp();
                sleep(100);
                drive.moveGyro(.4, 10, 90);
                drive.armDown();

            }
            else if (skystonePos == "2 & 5"){
                drive.moveGyro(-.4, 11, -90);
                sleep(250);
                drive.turn(-180, .3);
                sleep(250);
                drive.moveGyro(-.4, 16.5, -180);
                drive.armDown();
                sleep(100);
                drive.moveGyro(.4, 8, -180);
                sleep(250);
                drive.turn(90,.3);
                sleep(250);
                drive.moveGyro(-.4, 65, 90);
                drive.armUp();
                sleep(100);
                drive.moveGyro(.4, 10, 90);
                drive.armDown();


            }
            else{
                drive.moveGyro(-.4, 20, -90);
                sleep(250);
                drive.turn(-180, .3);
                sleep(250);
                drive.moveGyro(-.4, 16.5, -180);
                drive.armDown();
                sleep(100);
                drive.moveGyro(.4, 8, -180);
                sleep(250);
                drive.turn(90,.3);
                sleep(250);
                drive.moveGyro(-.4, 80, 90);
                drive.armUp();
                sleep(100);
                drive.moveGyro(.4, 10, 90);
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