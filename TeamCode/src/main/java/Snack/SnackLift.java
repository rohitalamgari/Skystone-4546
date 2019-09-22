package Snack;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SnackLift extends SnackInterface {
    public DcMotor mtrLift = null;

    public void init(HardwareMap hwmap, Telemetry myTelemetry) {
        super.init(hwmap, myTelemetry);
        mtrLift = hwmap.dcMotor.get("mtrLift");
    }
}
