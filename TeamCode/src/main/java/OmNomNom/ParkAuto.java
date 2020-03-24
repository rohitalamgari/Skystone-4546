package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;
import Snack.SnackLift;

@Autonomous(name = "Park Auto", group = "4546")
public class ParkAuto extends LinearOpMode{
    SnackDrive drive = new SnackDrive();
    SnackLift lift = new SnackLift();

    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap, telemetry);
        lift.init(hardwareMap, telemetry);
        lift.clawInit();
        drive.gateRelease();

        String parkChoice = null;

        while (!isStarted()){
            if (gamepad1.dpad_up)
                parkChoice = "TopToRight";
            else if (gamepad1.dpad_down)
                parkChoice = "TopToLeft";
            else if (gamepad1.dpad_right)
                parkChoice = "BottomToRight";
            else if (gamepad1.dpad_left)
                parkChoice = "BottomToLeft";
            telemetry.addData("Parking Path: ", parkChoice);
            telemetry.update();
        }
        waitForStart();

        if (!isStopRequested()){
            if (parkChoice.equals("TopToRight")){
                drive.moveGyro(.4, 17, 0);
                sleep(350);
                drive.turn(-90, .35);
                sleep(450);
                drive.moveGyro(-.4, 44, -90);
                sleep(250);
                drive.strafeRightInches(.35, 7);
            }

            else if (parkChoice.equals("TopToLeft")){
                drive.moveGyro(.4, 17, 0);
                sleep(350);
                drive.turn(90, .35);
                sleep(450);
                drive.moveGyro(-.4, 44, -90);
                sleep(250);
                drive.strafeLeftInches(.35, 7);
            }

            else if (parkChoice.equals("BottomToRight")){
                drive.strafeRightInches(.35, 3);
                sleep(200);
                drive.moveGyro(-.35, 37, 0);
                sleep(250);
                drive.strafeLeftInches(.35, 10);
            }

            else if (parkChoice.equals("BottomToLeft")){
                drive.strafeLeftInches(.35, 3);
                sleep(200);
                drive.moveGyro(-.35, 37, 0);
                sleep(250);
                drive.strafeRightInches(.35, 10);
            }

            else{
                sleep(30000);
            }
        }

    }
}
