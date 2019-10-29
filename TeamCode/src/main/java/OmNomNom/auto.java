package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;

@Autonomous(name = "auto test", group = "4546")
public class auto extends LinearOpMode {

    SnackDrive drive = new SnackDrive();

    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap, telemetry);
        waitForStart();
        if(!isStopRequested()){
            goAlternatePark(true);
            //drive.turn(90, 0.5);
            //telemetry.addData("Current position", drive.gyroYaw());
            //telemetry.addData("Current Difference",drive.angleDiff(90));
            //telemetry.update();
        }
    }
    public void goPark(){
        drive.moveGyro(0.3,12,0);
    }
    //true is blue
    public void goAlternatePark(boolean color){
        //while (drive.blueCount() < 200 {
            if (color){
                drive.moveGyro(0.3, 30, 0);
                drive.turn(90, 0.5);
                while (drive.blueCount() < 200){
                    drive.go(0.3);
                }
                stop();
            }
            else {
                drive.moveGyro(0.3, 30, 0);
                drive.turn(-90, 0.5);
                while (drive.redCount() < 250) {
                    drive.go(0.3);
                }
                stop();
            }
        }

}
