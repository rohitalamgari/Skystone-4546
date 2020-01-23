package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;
import Snack.SnackLift;
import Snack.VuforiaBitmap;

@Autonomous(name = "Blue Quarry Auto", group = "4546")
public class blueQuarryAuto extends LinearOpMode {

    SnackDrive drive = new SnackDrive();
    SnackLift lift = new SnackLift();
    String skystonePos = "not found";

    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaBitmap sample = new VuforiaBitmap(this);
        drive.init(hardwareMap, telemetry);
        lift.init(hardwareMap, telemetry);
        while (!isStarted()){
            skystonePos = sample.skystonePosition;
            telemetry.addData("medX: ", sample.medX(true));
            telemetry.addData("skystonePos", skystonePos);
            telemetry.update();
            sleep(1000);
        }
        waitForStart();



        if(!isStopRequested()){
            //goAlternatePark(true);
            if (skystonePos == "1 & 4"){

                //skystone 1

                drive.strafeLeftInches(.4, 12);
                lift.release();
                drive.moveGyro(-.2, 4, 0);
                sleep(250);
                drive.moveGyro(.4, 35, 0);
                sleep(100);
                lift.grab();
                drive.moveGyro(-.4, 10, 0);
                sleep(250);
                drive.turn(-90, .35);
                drive.moveGyro(.4, 25, -90);

            }
            else if (skystonePos == "2 & 5"){



            }
            else{


            }

        }
    }

}