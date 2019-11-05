package OmNomNom;

import Snack.VuforiaBitmap;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "VuforiaBitmapTest", group = "4546")
public class VuforiaBitmapTest extends LinearOpMode {

    private VuforiaBitmap sample;

    @Override
    public void runOpMode() throws InterruptedException{
        VuforiaBitmap sample = new VuforiaBitmap(this);

        while (!isStarted()){
            telemetry.addData("medX: ", sample.medX(true));
            telemetry.update();
            sleep(1000);
        }
        waitForStart();
    }
}
