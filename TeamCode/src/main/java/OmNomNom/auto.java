package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;
import Snack.SnackLift;
import Snack.VuforiaBitmap;

@Autonomous(name = "Test2 Auto", group = "4546")
public class auto extends LinearOpMode {

    SnackDrive drive = new SnackDrive();
    SnackLift lift = new SnackLift();

    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap,telemetry);
        lift.init(hardwareMap, telemetry);

        waitForStart();
        if(!isStopRequested()) {
            lift.grab();
            sleep(500);
            drive.turn(90, .15);
        }
    }



}
