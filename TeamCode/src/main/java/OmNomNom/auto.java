package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;
import Snack.VuforiaBitmap;

@Autonomous(name = "auto test", group = "4546")
public class auto extends LinearOpMode {

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
                drive.moveGyro(.3,39,0);
                sleep(500);
                drive.turn(0,.3);
            }
            else if (skystonePos == "2 & 5"){
                drive.moveGyro(.3,39,0);
                sleep(500);
                drive.turn(0,.3);
            }
            else{
                drive.moveGyro(.3,39,0);
                sleep(500);
                drive.turn(0,.3);
            }
        }
    }
    public void goPark(){
        drive.moveGyro(0.3,12,0);
    }
    //true is blue
    public void goAlternatePark(boolean color) throws InterruptedException{
        //while (drive.blueCount() < 200 {
            if (color){
                drive.moveGyro(0.3, 30, 0);
                drive.turn(-90, 0.5);
                while (drive.blueCount() < 200){
                    drive.go(0.3);
                }
                stop();
            }
            else {
                drive.moveGyro(0.3, 30, 0);
                drive.turn(90, 0.5);
                while (drive.redCount() < 250) {
                    drive.go(0.3);
                }
                stop();
            }
        }

}
