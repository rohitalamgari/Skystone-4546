package OmNomNom;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "SnackTeleop", group = "4546")
public class SnackTeleOp extends SnackOpMode{
    @Override
    public void init() {
        init(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        driveTrainPower(-gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_stick_x/2);
        telemetry.addData("Is the gyro working", snackDrive.gyro.getAngularOrientation());
        telemetry.addData("Gyro Yaw", snackDrive.gyroYaw());
        telemetry.addData("encoders per inch", snackDrive.countsPerInch);
        telemetry.addData("color sensor blue count",snackDrive.blueCount());
        telemetry.addData("color sensor red count",snackDrive.redCount());
    }
}
