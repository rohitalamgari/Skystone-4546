package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Snack.SnackDrive;

public abstract class SnackOpMode extends OpMode {
    SnackDrive snackDrive = new SnackDrive();
    public void init(HardwareMap hwmap, Telemetry telemetry) {
        snackDrive.init(hwmap, telemetry);
    }


    public double WeightAvg(double x, double y, double z) {
        double speed_D = 0;


        if ((Math.abs(x) + Math.abs(y) + Math.abs(z))  != 0.0) {
            speed_D = ((x * Math.abs(x)) + (y * Math.abs(y)) + (z * Math.abs(z)))
                    / (Math.abs(x) + Math.abs(y) + Math.abs(z));
        }
        return (speed_D);
    }

    public void driveTrainPower(double forward, double strafe, double rotate){

        snackDrive.mtrFR.setPower(WeightAvg(forward,strafe,-rotate));
        snackDrive.mtrFL.setPower(WeightAvg(forward,-strafe,rotate));
        snackDrive.mtrBR.setPower(WeightAvg(forward,-strafe,-rotate));
        snackDrive.mtrBL.setPower(WeightAvg(forward,strafe,rotate));
    }

}