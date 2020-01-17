package Snack;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SnackLift extends SnackInterface {
    // public DcMotor mtrLift1 = null;
    // public DcMotor mtrLift2 = null;
    public DcMotor mtrLift = null;
    public Servo srvClaw1 = null;
    public Servo srvClaw2 = null;

    public void init(HardwareMap hwmap, Telemetry myTelemetry) {
        super.init(hwmap, myTelemetry);
        mtrLift = hwmap.dcMotor.get("mtrLift");
        mtrLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        srvClaw1 = hwmap.servo.get("srvClaw1");
        srvClaw2 = hwmap.servo.get("srvClaw2");
    }

    public void resetEncoders(){
        mtrLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void grab(){
        srvClaw1.setPosition(0);
        srvClaw2.setPosition(0);
    }

    public void release(){
        srvClaw1.setPosition(.5);
        srvClaw2.setPosition(.5);
    }

    public void clawInit(){
        srvClaw1.setPosition(1);
        srvClaw2.setPosition(1);
    }



}
