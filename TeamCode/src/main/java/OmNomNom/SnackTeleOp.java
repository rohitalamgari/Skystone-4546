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

        if (gamepad1.right_bumper){
            flip = true;
        }
        else if (gamepad1.left_bumper){
            flip = false;
        }

        if (gamepad1.y){
            snackDrive.armUp();
        }
        if (gamepad1.a){
            snackDrive.armDown();
        }

        if (gamepad1.dpad_up){
            snackDrive.capUp();
        }
        if (gamepad1.dpad_down){
            snackDrive.capInit();
        }


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
