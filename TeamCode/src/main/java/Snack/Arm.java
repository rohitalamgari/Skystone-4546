package Snack;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm extends SnackInterface {
    public DcMotor mtrArm = null;

    public void init(HardwareMap hwmap, Telemetry myTelemetry) {
        super.init(hwmap, myTelemetry);
        mtrArm = hwmap.dcMotor.get("mtrArm");
        mtrArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mtrArm.setDirection(DcMotor.Direction.REVERSE);
    }

    public void flipperUp(double position){
        mtrArm.setTargetPosition((int) (-position));
    }

    public void flipperDown(double position){
        mtrArm.setTargetPosition((int) (-position));
    }

    public void stop(){
        mtrArm.setPower(0);
    }
}
