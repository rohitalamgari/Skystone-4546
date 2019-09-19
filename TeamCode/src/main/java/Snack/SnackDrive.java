package Snack;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SnackDrive extends SnackInterface {
    public DcMotor mtrFL = null;
    public DcMotor mtrFR = null;
    public DcMotor mtrBL = null;
    public DcMotor mtrBR = null;

    public DcMotor[] motors = null;

    public void init(HardwareMap hwmap, Telemetry telemetry){
        super.init(hwmap, telemetry);
        mtrFL = hwmap.dcMotor.get("mtrFL");
        mtrFR = hwmap.dcMotor.get("mtrFR");
        mtrBL = hwmap.dcMotor.get("mtrBL");
        mtrBR = hwmap.dcMotor.get("mtrBR");
        motors = new DcMotor[]{mtrFL, mtrFR, mtrBL, mtrBR};
    }



}
