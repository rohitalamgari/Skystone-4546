package Snack;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SnackCollector extends SnackInterface {
    public CRServo csrvIntakeL = null;
    public CRServo csrvIntakeR=  null;

    //public DcMotorEx test = null;

    public CRServo[] csrvs = null;

    public void init(HardwareMap hwmap, Telemetry myTelemetry) {
        super.init(hwmap, myTelemetry);
        csrvIntakeL = hwmap.crservo.get("csrvIntakeL");
        csrvIntakeR = hwmap.crservo.get("csrvIntakeR");
        csrvIntakeR.setDirection(DcMotorSimple.Direction.REVERSE);

        csrvs = new CRServo[]{csrvIntakeL, csrvIntakeR};
        myTelemetry.addData("Collector", "Initialized");
    }

    public void intake(double speed){
        speed *= 0.75;
        for (CRServo crServo : csrvs) crServo.setPower(speed);

    }



}
