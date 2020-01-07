package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "SnackTeleop", group = "4546")
public class SnackTeleOp extends SnackOpMode{
    @Override
    public void init() {
        init(hardwareMap, telemetry);
    }
    //final double armStart = snackLift.mtrArm.getCurrentPosition();
    double a = 0;
    boolean flip = false;

    @Override
    public void loop() {

        if (flip){
            driveTrainPower(gamepad1.left_stick_y, gamepad1.left_stick_x, -gamepad1.right_stick_x/2);
        }
        else{
            driveTrainPower(-gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_stick_x/2);
        }
        if (gamepad1.right_bumper && !flip){
            flip = true;
        }
        else if (gamepad1.right_bumper && flip){
            flip = false;
        }

        if (gamepad1.y){
            snackDrive.armUp();
        }
        if (gamepad1.a){
            snackDrive.armDown();
        }
        if (gamepad2.dpad_up){

        }
        if (gamepad2.dpad_down){

        }

        if (gamepad2.right_bumper){
            snackLift.grabUp();
        }
        if (gamepad2.left_bumper){
            snackLift.grabDown();
        }
        if (gamepad2.right_trigger > .1){
            //double newPos = armStart + 175 * gamepad2.right_trigger;
            //double powerVar = (Math.abs(newPos - snackLift.mtrArm.getCurrentPosition())/100);
            //snackLift.armTarget((int)newPos, (-(0.5 * powerVar) - 0.2));
            snackLift.mtrArm.setPower(-.3);
        }
        else if (gamepad2.left_trigger > .1){
            snackLift.mtrArm.setPower(.3);
        }
        else{
            snackLift.mtrArm.setPower(0);
        }

        telemetry.addData("Is the gyro working", snackDrive.gyro.getAngularOrientation());
        telemetry.addData("Gyro Yaw", snackDrive.gyroYaw());
        telemetry.addData("encoders per inch", snackDrive.countsPerInch);
        telemetry.addData("encoders for arm", snackLift.mtrArm.getCurrentPosition());
        telemetry.addData("pos for srv srm", snackDrive.srvArm.getPosition());
        telemetry.update();


    }
}
