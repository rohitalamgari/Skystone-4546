package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Snack.SnackLift;

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
            driveTrainPower(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x/2);
        }
        else{
            driveTrainPower(-gamepad1.left_stick_y, gamepad1.left_stick_x, -gamepad1.right_stick_x/2);
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
            snackLift.armTarget(90, .45);
        }
        if (gamepad2.dpad_down){
            snackLift.armTarget(25, -.45);
        }

        if (gamepad2.right_bumper){
            snackLift.grabUp();
        }
        if (gamepad2.left_bumper){
            snackLift.grabDown();
        }
        if (gamepad2.right_trigger > .1){
            snackLift.mtrArm.setTargetPosition(-105);
            snackLift.mtrArm.setPower(-.5);
        }
        else if (gamepad2.left_trigger > .1){
            snackLift.mtrArm.setPower(.5);
        }
        else{
            snackLift.mtrArm.setPower(0);
        }

        telemetry.addData("Gyro Yaw", snackDrive.gyroYaw());
        telemetry.addData("encoders for arm", snackLift.mtrArm.getCurrentPosition());
        telemetry.update();


    }
}
