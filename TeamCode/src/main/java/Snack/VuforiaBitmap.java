package Snack;

import android.graphics.Bitmap;

import static android.graphics.Color.red;
import static android.graphics.Color.green;
import static android.graphics.Color.blue;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Frame;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;

public class VuforiaBitmap {

    private LinearOpMode opMode;

    private VuforiaLocalizer vuforia;
    private Parameters parameters;
    private CameraDirection CAMERA_CHOICE = CameraDirection.BACK;
    private static final String VUFORIA_KEY = "Acwi41P/////AAABmXAF5Uahj0aglVwEx0GLTotkFwuYvGa385NRnC3GmFdHiha7BKdStHJwB6nj4zrSBLOJ0jGEICqTReR3LiErc63MaNJf8NR/J8TUk6MOaF8xM5fa5uDU3J/7/tys+Hu1G5nlncWy3gGsHrU8lwG/rL+G0R/caVfNp0GfRtpcH7LMLDZOslSc+URv9+IF8+C0jA4JzTfM4lRkOEcIqIyTs20EZC+W3QYI7o7n700hOwq+WpoG7qMgqcrgk3+B1/hTLICE3fodM/34CQjbEONYKpGbj8IOG714CeY9qyI6WhainXidKda/QAslXEvYCDvBCZoGW/4I3TaZAJUWAeD1l5SeL/m4nuJxV9Jmai/0/9Qn";

    public static String skystonePosition = "notFound";

    private final int RED_THRESHOLD = 35;
    private final int GREEN_THRESHOLD = 35;
    private final int BLUE_THRESHOLD = 35;

    public VuforiaBitmap(LinearOpMode opMode) {

        this.opMode = opMode;

        int cameraMonitorViewId = this.opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", this.opMode.hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        params.vuforiaLicenseKey = VUFORIA_KEY;
        params.cameraDirection = CAMERA_CHOICE;
        params.cameraName = opMode.hardwareMap.get(WebcamName.class, "Webcam 1");
        vuforia = ClassFactory.getInstance().createVuforia(params);

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        vuforia.setFrameQueueCapacity(4);
        vuforia.enableConvertFrameToBitmap();

    }

    public Bitmap getBitmap() throws InterruptedException {

        VuforiaLocalizer.CloseableFrame picture;
        picture = vuforia.getFrameQueue().take();
        Image rgb = picture.getImage(1);

        long numImages = picture.getNumImages();

        opMode.telemetry.addData("Num images", numImages);
        opMode.telemetry.update();

        for (int i = 0; i < numImages; i++) {

            int format = picture.getImage(i).getFormat();
            if (format == PIXEL_FORMAT.RGB565) {
                rgb = picture.getImage(i);
                break;
            } else {
                opMode.telemetry.addLine("Didn't find correct RGB format");
                opMode.telemetry.update();


            }
        }

        Bitmap imageBitmap = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
        imageBitmap.copyPixelsFromBuffer(rgb.getPixels());

        opMode.telemetry.addData("Image width", imageBitmap.getWidth());
        opMode.telemetry.addData("Image height", imageBitmap.getHeight());
        opMode.telemetry.update();
        opMode.sleep(500);

        picture.close();


        return imageBitmap;
    }

    public double getImageHeight() throws InterruptedException {
        Bitmap bitmap = getBitmap();
        return bitmap.getHeight();
    }

    public double getImageWidth() throws InterruptedException {
        Bitmap bitmap = getBitmap();
        return bitmap.getWidth();
    }

    //True for Blue
    public double avgX(boolean color) throws InterruptedException {
        double avgX = 0;
        double avgY = 0;
        double medX = 0;
        double medY = 0;
        Bitmap bitmap = getBitmap();
        int skystonePixelCount = 0;
        ArrayList<Integer> xValues = new ArrayList<>();
        ArrayList<Integer> yValues = new ArrayList<>();

        for (int y = 0; y < bitmap.getHeight(); y++) {
            for (int x = 0; x < bitmap.getWidth(); x++) {
                int pixel = bitmap.getPixel(x, y);
                if (red(pixel) <= RED_THRESHOLD && blue(pixel) <= BLUE_THRESHOLD && green(pixel) <= GREEN_THRESHOLD) {
                    xValues.add(x);
                    yValues.add(y);
                }
            }
        }

        for (int xCoor : xValues) {
            avgX += xCoor;
        }
        for (int yCoor : yValues) {
            avgY += yCoor;
        }
        Collections.sort(xValues);
        Collections.sort(yValues);
        medX = xValues.get(xValues.size()/2);
        medY = yValues.get(yValues.size()/2);
        avgX /= xValues.size();
        avgY /= yValues.size();
        if (color) {
            if (medX < 400 && xValues.size() > 10000) {
                skystonePosition = "1 & 4";
                opMode.telemetry.addData("skystonePosition: ", skystonePosition);
                opMode.telemetry.addData("xValues size", xValues.size());
            } else if (medX < 620 && xValues.size() > 10000) {
                skystonePosition = "2 & 5";
                opMode.telemetry.addData("skystonePosition: ", skystonePosition);
                opMode.telemetry.addData("xvalues size", xValues.size());
            } else {
                skystonePosition = "3 & 6";
                opMode.telemetry.addData("skystonePosition: ", skystonePosition);
                opMode.telemetry.addData("xvalues size", xValues.size());
            }
            opMode.telemetry.update();
            opMode.sleep(1000);
        }
        else{
            if (medX < 230 && xValues.size() > 19000) {
                skystonePosition = "2 & 5";
                opMode.telemetry.addData("skystonePosition: ", skystonePosition);
                opMode.telemetry.addData("xValues size", xValues.size());
            } else if (medX < 400 && xValues.size() > 19000) {
                skystonePosition = "1 & 4";
                opMode.telemetry.addData("skystonePosition: ", skystonePosition);
                opMode.telemetry.addData("xvalues size", xValues.size());
            } else {
                skystonePosition = "3 & 6";
                opMode.telemetry.addData("skystonePosition: ", skystonePosition);
                opMode.telemetry.addData("xvalues size", xValues.size());
            }
            opMode.telemetry.update();
            opMode.sleep(1000);
        }
        return medX;
    }
}