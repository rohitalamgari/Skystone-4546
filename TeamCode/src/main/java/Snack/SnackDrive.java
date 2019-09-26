package Snack;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;

public class SnackDrive extends SnackInterface {
    public DcMotor mtrFL = null;
    public DcMotor mtrFR = null;
    public DcMotor mtrBL = null;
    public DcMotor mtrBR = null;

    public DcMotor[] motors = null;

    double countsPerInch = EncodersPerInch(560, 0.6, (100/25.4));


    ElapsedTime times;

    //sensors
    public BNO055IMU gyro;
    Orientation angles;
    Acceleration gravity;
    BNO055IMU.Parameters parameters;



    public void init(HardwareMap hwmap, Telemetry telemetry){
        super.init(hwmap, telemetry);
        mtrFL = hwmap.dcMotor.get("mtrFL");
        mtrFR = hwmap.dcMotor.get("mtrFR");
        mtrBL = hwmap.dcMotor.get("mtrBL");
        mtrBR = hwmap.dcMotor.get("mtrBR");
        motors = new DcMotor[]{mtrFL, mtrFR, mtrBL, mtrBR};

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        gyro = hwmap.get(BNO055IMU.class, "imu");
        gyro.initialize(parameters);
    }



    public void resetMode(){
        for (DcMotor m: motors) m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setEncoderMode(){
        for (DcMotor m : motors) m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void resetEncoders(){
        for (DcMotor m : motors) m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // encoder method that get avg encoders of all wheels
    /*public double getEncoderAvg(){
        int count = 4;
        if (mtrFL.getCurrentPosition() == 0){
            count--;
        }
        if (mtrFR.getCurrentPosition() == 0){
            count--;
        }
        if (mtrBL.getCurrentPosition() == 0){
            count--;
        }
        if (mtrBR.getCurrentPosition() == 0){
            count--;
        }
        if (count == 0) count++;
        return (Math.abs(mtrFL.getCurrentPosition()) +
                Math.abs(mtrFR.getCurrentPosition()) +
                Math.abs(mtrBL.getCurrentPosition()) +
                Math.abs(mtrBR.getCurrentPosition())) / count;
    }

    // encoder method that get avg encoders of left wheels
    public double getEncoderL(){
        int count = 2;
        if (mtrFL.getCurrentPosition() == 0){
            count--;
        }
        if (mtrBL.getCurrentPosition() == 0){
            count--;
        }
        if (count == 0) count++;
        return (Math.abs(mtrFL.getCurrentPosition()) +
                Math.abs(mtrBL.getCurrentPosition())) / count;
    }

    // encoder method that get avg encoders of right wheels
    public double getEncoderR(){
        int count = 2;
        if (mtrFR.getCurrentPosition() == 0){
            count--;
        }
        if (mtrBR.getCurrentPosition() == 0){
            count--;
        }
        if (count == 0) count++;
        return (Math.abs(mtrFR.getCurrentPosition()) +
                Math.abs(mtrBR.getCurrentPosition())) / count;
    }*/

    public void go(double speed){
        for (DcMotor m : motors) m.setPower(speed);
    }

    public void stop(){
        for (DcMotor m: motors) m.setPower(0);
    }

    public void setTargetPosition(double inches){
        double target = inches * countsPerInch;
        for (DcMotor m : motors) m.setTargetPosition((int) target);
    }


    public void goInches(double inches, double speed){
        resetEncoders();
        setEncoderMode();
        setTargetPosition(inches);
        go(speed);
        resetMode();
        stop();
    }



    public void updateValues(){
        angles = gyro.getAngularOrientation();
    }

    public boolean resetGyro(){
        return gyro.initialize(parameters);
    }

    //returns positive angle to turn right & negative angle to turn left
    public double angleDiff(double goalAngle) {
        double currAngle = gyroYaw();
        if (currAngle >= 0 && goalAngle >= 0 || currAngle <= 0 && goalAngle <= 0) { //curr & goal are both positive or both negative
            return -(currAngle - goalAngle);
        } else if (Math.abs(currAngle - goalAngle) <= 180) {//diff btwn curr & goal is less than or equal to 180
            return -(currAngle - goalAngle);
        } else if (currAngle > goalAngle) {//curr is greater than goal
            return (360 - (currAngle - goalAngle));
        } else {//goal is greater than curr
            return -(360 + (currAngle - goalAngle));
        }
    }

    //gyro method to get yaw
    public double gyroYaw(){
        updateValues();
        double yaw = angles.firstAngle * -1;
        if (angles.firstAngle < -180){
            yaw -= 360;
        }
        return yaw;
    }

    //gyro method to get pitch
    public double gyroPitch(){
        updateValues();
        double pitch = angles.secondAngle;
        return pitch;
    }

    //gyro method to get roll
    public double gyroRoll(){
        updateValues();
        double roll = angles.thirdAngle;
        return roll;
    }

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}
