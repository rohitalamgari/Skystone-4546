package OmNomNom;

public class SnackTeleOp extends SnackOpMode{
    @Override
    public void init() {
        init(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        driveTrainPower(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        //snackLift.mtrLift.setPower(gamepad2.right_stick_y);
        //snackCollector.intake(gamepad2.left_stick_y);
    }
}
