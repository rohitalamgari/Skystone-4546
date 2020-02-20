package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Snack.SnackDrive;
import Snack.SnackLift;

@Autonomous(name = "RedSaber", group = "4546")
public class redSaberAuto extends LinearOpMode {
    SnackDrive drive = new SnackDrive();
    SnackLift lift = new SnackLift();
    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap, telemetry);
        lift.init(hardwareMap, telemetry);
        lift.clawInit();
        drive.gateRelease();

        boolean push = false;

        while (!isStarted()){
            if (gamepad1.dpad_up) push = true;
            else if (gamepad1.dpad_down) push = false;
            telemetry.addData("push : ", push);
            telemetry.update();
        }

        waitForStart();
        if (!isStopRequested()){
            lift.clawInit();
            sleep(100);
            //Moves and strafes towards foundation
            drive.moveGyro(0.45,6,0);
            sleep(250);
            drive.strafeRightInches(.4, 14);
            //moves up and grabs it
            sleep(250);
            drive.moveGyro(0.45,28,0);
            sleep(100);
            drive.moveGyro(.3, 5, 0);
            sleep(300);
            drive.foundationDown();
            //comes back and turns
            sleep(750);
            drive.moveGyro(-0.55,55,0);
            drive.foundationUp();
            sleep(15000);
            drive.strafeLeftInches(.5, 32.5);
            lift.clawInit();
            sleep(100);
            if (push){
                drive.moveGyro(.35, 8.75, 0);
                sleep(200);
                drive.turn(90, .35);
                sleep(250);
                drive.moveGyroTime(.25, 20, 90, 2500);
                sleep(200);
                drive.strafeRightInches(.35, 9);
                sleep(200);
                drive.moveGyro(-.35, 35.5, 90);
                sleep(250);
                drive.strafeRightInches(.35, 10);
            }
            else{
                sleep(200);
                drive.moveGyro(.35, 4.75, 0);
                sleep(300);
                drive.turn(90, .35);
                sleep(250);
                drive.strafeRightInches(.35, 5);
                sleep(200);
                drive.moveGyro(-.35, 27.24, 90);
                sleep(250);
                drive.strafeRightInches(.35, 5);
            }
        }
    }
}
