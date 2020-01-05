package Snack;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SnackLift extends SnackInterface {
    // public DcMotor mtrLift1 = null;
    // public DcMotor mtrLift2 = null;
    public DcMotor mtrArm = null;

    public void init(HardwareMap hwmap, Telemetry myTelemetry) {
        super.init(hwmap, myTelemetry);
        // mtrLift1 = hwmap.dcMotor.get("mtrLift1");
        // mtrLift2 = hwmap.dcMotor.get("mtrLift2");
        mtrArm = hwmap.dcMotor.get("mtrArm");
        mtrArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //armTarget(0, 0.3);
    }

    public void armTarget(int target, double power){
        mtrArm.setTargetPosition(target);
        while(Math.abs(mtrArm.getCurrentPosition() - target) > 2){
            mtrArm.setPower(power);
        }
        mtrArm.setPower(0);
    }
}
