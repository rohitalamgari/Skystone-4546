package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Snack.SnackDrive;

@Autonomous(name = "auto test", group = "4546")
public class auto extends LinearOpMode {

    SnackDrive drive = new SnackDrive();

    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap, telemetry);
        waitForStart();
        if(!isStopRequested()){
            //drive.moveGyro(.3, 100, 0);
            drive.turnP(90, 0.7);
            telemetry.addData("mtrFL position", drive.mtrFL.getCurrentPosition());
            telemetry.addData("mtrFR position", drive.mtrFR.getCurrentPosition());
            telemetry.addData("mtrBL position", drive.mtrBL.getCurrentPosition());
            telemetry.addData("mtrBR position", drive.mtrBR.getCurrentPosition());
        }
    }
}
