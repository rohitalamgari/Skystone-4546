package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Snack.SnackLift;

@TeleOp (name = "SnackTeleop", group = "4546")
public class SnackTeleOp extends SnackOpMode{
    @Override
    public void init() {
        init(hardwareMap, telemetry);
        snackLift.clawInit();
    }
    //final double armStart = snackLift.mtrArm.getCurrentPosition();
    double a = 0;
    boolean flip = false;

    @Override
    public void loop() {

        double k = 1.0;
        if(gamepad1.left_trigger > .6){
            k = 0.275;
        }
        else{
            k = 1.0;
        }
        if (flip){
            driveTrainPower(gamepad1.left_stick_y * k, -gamepad1.left_stick_x * k, -gamepad1.right_stick_x * .6 * k);
        }
        else{
            driveTrainPower(-gamepad1.left_stick_y * k, gamepad1.left_stick_x * k, -gamepad1.right_stick_x * .6 * k);
        }



        if (gamepad1.right_bumper){
            flip = true;
        }
        else if (gamepad1.left_bumper){
            flip = false;
        }

        if (gamepad1.y){
            snackLift.release();
        }
        if (gamepad1.a){
            snackLift.grab();
        }
        if (gamepad1.dpad_up){
            snackLift.clawInit();
        }


        if(gamepad1.x)
            snackDrive.gateGrab();
        if (gamepad1.b)
            snackDrive.gateRelease();

        if (gamepad2.dpad_up)
            snackDrive.foundationUp();

        if (gamepad2.dpad_down)
            snackDrive.foundationDown();

        if (gamepad2.right_bumper){
            snackLift.grab();
        }
        if (gamepad2.left_bumper){
            snackLift.release();
        }

        if (gamepad2.right_trigger > .1){
            snackLift.mtrLift.setPower(1);
        }
        else if (gamepad2.left_trigger > .1){
            snackLift.mtrLift.setPower(-1);
        }
        else{
            snackLift.mtrLift.setPower(0);
        }

        telemetry.addData("flip: ", flip);
        telemetry.update();


    }
}
