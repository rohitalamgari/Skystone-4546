package Snack;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
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

    //public CRServo csrvArm = null;
   // LinearOpMode opMode;

    public Servo srvArm = null;
    public Servo srvCap = null;


    public DcMotor[] motors = null;

    public double countsPerInch = EncodersPerInch(560, 0.6, (100/25.4));


    ElapsedTime time = new ElapsedTime();

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

        mtrFL.setDirection(DcMotorSimple.Direction.REVERSE);
        mtrBL.setDirection(DcMotorSimple.Direction.REVERSE);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        gyro = hwmap.get(BNO055IMU.class, "imu");
        gyro.initialize(parameters);



        srvArm = hwmap.servo.get("srvArm");
        srvCap = hwmap.servo.get("srvCap");


        armDown();
        capInit();

        telemetry.addData("Drivetrain", "Initialized");
    }
//    public void resetMode(){
//        for (DcMotor m: motors) m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//    }
//
//    public void setEncoderMode(){
//        for (DcMotor m : motors) m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }
//
//    public void resetEncoders(){
//        for (DcMotor m : motors) m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//    }


    public void resetEncoders(){
        for (DcMotor m : motors){
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       //     opMode.idle();
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
     //       opMode.idle();
        }
    }

    public void capInit(){
        srvCap.setPosition(1);
    }

    public void capUp(){
        srvCap.setPosition(.2);
    }

    public void go(double speed){
        mtrBR.setPower(speed);
        mtrBL.setPower(speed*1.15);
        mtrFL.setPower(speed*1.15);
        mtrFR.setPower(speed);
    }
    public void startMotors(double lp, double rp){
        mtrFL.setPower(lp);
        mtrBL.setPower(lp);
        mtrFR.setPower(rp);
        mtrBR.setPower(rp);
    }

    public void stop(){
        for (DcMotor m: motors){
            m.setPower(0);
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    // encoder method that get avg encoders of all wheels
    public double getEncoderAvg(){
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
    }

    public void moveGyro(double power, double inches, double angle){
        resetEncoders();
        if (power > 0){
            while (getEncoderAvg() < inches * countsPerInch){
                if (angleDiff(angle) > 2){
                    startMotors(power * 0.7, power);
                }
                else if (angleDiff(angle) < -2){
                    startMotors(power, power * 0.7);
                }
                else{
                    go(power);
                }
                privateTelemetry.addData("angle", gyroYaw());
                privateTelemetry.addData("angle diff", angleDiff(angle));
                privateTelemetry.update();
            }
        }
        else{
            while (getEncoderAvg() < inches * countsPerInch){
                if (angleDiff(angle) > 2){
                    startMotors(power, power*.7);
                }
                else if (angleDiff(angle) < -2){
                    startMotors(power*.7, power);
                }
                else{
                    go(power);
                }
                privateTelemetry.addData("angle", gyroYaw());
                privateTelemetry.addData("angle diff", angleDiff(angle));
                privateTelemetry.update();
            }
        }
        stop();
    }

    //public void



//
//    public void setTargetPosition(double inches){
//        double target = inches * countsPerInch;
//        for (DcMotor m : motors) m.setTargetPosition((int) target);
//    }
//
//
//    public void goInches(double inches, double speed){
//        resetEncoders();
//        setEncoderMode();
//        setTargetPosition(inches);
//        go(speed);
//        resetMode();
//        stop();
//    }
//
    public void turn(double angle, double p) throws InterruptedException{
        double kP = p;
        final double startPos = gyroYaw();
        final double angleDiff = angle - startPos;
        double deltaAngle = angleDiff(angle);
        double changePID = 0;
        while(Math.abs(deltaAngle) > .6){
            deltaAngle = angleDiff(angle);
            changePID = ((deltaAngle/Math.abs(angleDiff)) * kP);
            if (changePID < 0){
                startMotors(-changePID + .15 , changePID - .15);
            }
            else{
                startMotors(-changePID - .15, changePID + .15);

            }
            privateTelemetry.addData("Current position", gyroYaw());
            privateTelemetry.addData("Current Difference",deltaAngle);
            privateTelemetry.addData("changePID", changePID);
            privateTelemetry.addData("angleDiff: ", angleDiff);
            for(DcMotor m : motors){
                privateTelemetry.addData("motor", m.getCurrentPosition());
            }

            privateTelemetry.update();
        }
        stop();
    }

    public void turnD(double angle, double p, double d) throws InterruptedException{
        time.reset();
        double kP = p/2;
        double kD = d;
        double currentTime = time.milliseconds();
        double pastTime = 0;
        final double startPos = gyroYaw();
        final double angleDiff = angle - startPos;
        double prevDeltaAngle = angleDiff(angle);
        double deltaAngle = prevDeltaAngle;
        double changePID = 0;
        while(Math.abs(deltaAngle) > 1){
            deltaAngle = angleDiff(angle);
            pastTime = currentTime;
            currentTime = time.milliseconds();
            double dT = currentTime - pastTime;
            changePID = ((deltaAngle/Math.abs(angleDiff)) * kP) + ((deltaAngle - prevDeltaAngle)/(dT)*kD);
            if (changePID < 0){
                startMotors(changePID -.15, -changePID + .15);
            }
            else{
                startMotors(changePID + .15, -changePID - .15);

            }
            privateTelemetry.addData("Current position", gyroYaw());
            privateTelemetry.addData("Current Difference",deltaAngle);
            privateTelemetry.addData("changePID", changePID);
            privateTelemetry.addData("angleDiff: ", angleDiff);
            for(DcMotor m : motors){
                privateTelemetry.addData("motor", m.getCurrentPosition());
            }

            privateTelemetry.update();
            prevDeltaAngle = deltaAngle;
        }
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


    public void armUp(){srvArm.setPosition(.3); }

    public void armDown(){
        srvArm.setPosition(1);
    }

    /*
    public void foundationUp(){
        srvFoundationL.setPosition(0);
        srvFoundationR.setPosition(1);
    }
    public void foundationDown(){
        srvFoundationL.setPosition(1);
        srvFoundationR.setPosition(0);
    }
    */

    public void strafeRight(double powerF, double powerB){
        mtrBL.setPower(-powerB);
        mtrFL.setPower(powerF);
        mtrFR.setPower(-powerF);
        mtrBR.setPower(powerB);

    }

    public void strafeLeft(double powerF, double powerB){
        mtrBL.setPower(powerB);
        mtrFL.setPower(-powerF);
        mtrFR.setPower(powerF);
        mtrBR.setPower(-powerB);
    }

    public void strafeRightInches(double power, double inches){
        resetEncoders();
        while (getEncoderAvg() < inches * countsPerInch){
            mtrBL.setPower(-power);
            mtrFL.setPower(power);
            mtrFR.setPower(-power);
            mtrBR.setPower(power);
        }
    }

    public void strafeLeftInches(double power, double inches){
        resetEncoders();
        while (getEncoderAvg() < inches * countsPerInch){
            mtrBL.setPower(power);
            mtrFL.setPower(-power);
            mtrFR.setPower(power);
            mtrBR.setPower(-power);
        }
    }

    public void strafeGyro(double power, double inches, double heading){ //positive power is right and negative is left
        resetEncoders();
        if (power > 0){
            while (getEncoderAvg() < inches * countsPerInch){
                if (angleDiff(heading) > 2){
                    strafeRight(power, power * .7);
                }
                else if (angleDiff(heading) < -2){
                    strafeRight(power * .7, power);
                }
                else strafeRight(power, power);
                privateTelemetry.addData("angle", gyroYaw());
                privateTelemetry.addData("angle diff", angleDiff(heading));
                privateTelemetry.update();
            }
        }
        else {
            while (getEncoderAvg() < inches * countsPerInch){
                if (angleDiff(heading) > 2){
                    strafeLeft(-power * .7 , -power);
                }
                else if (angleDiff(heading) < -2){
                    strafeLeft(-power, -power * .7);
                }
                else strafeLeft(-power, -power);
                privateTelemetry.addData("angle", gyroYaw());
                privateTelemetry.addData("angle diff", angleDiff(heading));
                privateTelemetry.update();
            }

        }
    }


}