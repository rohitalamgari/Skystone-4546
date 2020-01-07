package Snack;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SnackLift extends SnackInterface {
    // public DcMotor mtrLift1 = null;
    // public DcMotor mtrLift2 = null;
    public DcMotor mtrArm = null;
    public Servo srvClaw = null;

    public void init(HardwareMap hwmap, Telemetry myTelemetry) {
        super.init(hwmap, myTelemetry);
        // mtrLift1 = hwmap.dcMotor.get("mtrLift1");
        // mtrLift2 = hwmap.dcMotor.get("mtrLift2");
        mtrArm = hwmap.dcMotor.get("mtrArm");
        mtrArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        srvClaw = hwmap.servo.get("srvClaw");
        //armTarget(0, 0.3);
    }

    public void armTarget(int target, double power){
        mtrArm.setTargetPosition(target);
        while(Math.abs(mtrArm.getCurrentPosition() - target) > 2){
            if (Math.abs(mtrArm.getCurrentPosition() - target) < (target/2)){
                mtrArm.setPower(power/2);
            }
            else{
                mtrArm.setPower(power);
            }
            privateTelemetry.addData("arm power:", power);
        }
        mtrArm.setPower(0);
    }

    public void grabDown(){
        srvClaw.setPosition(0);
    }

    public void grabUp(){
        srvClaw.setPosition(1);
    }

    public void armUp(){

    }

    public void armDown(){

    }
}
