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

            }

            else if (parkChoice.equals("BottomToRight")){

            }

            else if (parkChoice.equals("BottomToLeft")){

            }

            else{
                sleep(30000);
            }
        }

    }
}
