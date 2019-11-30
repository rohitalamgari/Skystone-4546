package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;
import Snack.VuforiaBitmap;

@Autonomous(name = "Test Auto", group = "4546")
public class auto extends LinearOpMode {

    SnackDrive drive = new SnackDrive();
    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap,telemetry);
        waitForStart();
        if(!isStopRequested())
        drive.strafeGyro(-.6, 60, 0);

    }



}
