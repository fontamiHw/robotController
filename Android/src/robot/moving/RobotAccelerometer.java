package robot.moving;

import robot.RobotMove;
import robot.controller.RobotController;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

public class RobotAccelerometer 
implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Display mDisplay;

    private float mSensorX;
    private float mSensorY;
    private Boolean initialized;
    private RobotMove robot;



    public RobotAccelerometer(RobotController robotController, RobotMove moveRobot) {
        initialized = false;
        // Get an instance of the SensorManager
        try {
            mSensorManager = (SensorManager) robotController.getSystemService(Activity.SENSOR_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Get an instance of the WindowManager
        WindowManager mWindowManager = (WindowManager) robotController.getSystemService(Activity.WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();

        robot = moveRobot;
    }


    
    public void onAccuracyChanged(Sensor sensor, int accuracy) {		
    }

    
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
        /*
         * record the accelerometer data, the event's timestamp as well as
         * the current time. The latter is needed so we can calculate the
         * "present" time during rendering. In this application, we need to
         * take into account how the screen is rotated with respect to the
         * sensors (which always return data in a coordinate space aligned
         * to with the screen in its native orientation).
         */

        int rotation = mDisplay.getRotation();

        switch (rotation) {
        case Surface.ROTATION_0:
            mSensorX = event.values[0];
            mSensorY = event.values[1];
            break;
        case Surface.ROTATION_90:
            mSensorX = -event.values[1];
            mSensorY = event.values[0];
            break;
        case Surface.ROTATION_180:
            mSensorX = -event.values[0];
            mSensorY = -event.values[1];
            break;
        case Surface.ROTATION_270:
            mSensorX = event.values[1]; 
            mSensorY = -event.values[0];
            break;
        }

        if (!initialized) {
            initialized = robot.initialization((int)mSensorX, 10-(int)mSensorY);				
        } else {

            robot.horizzontal((int) mSensorX);
            robot.vertical(10-(int) mSensorY);
        }

    }


    public void destroy() {
        mSensorManager.unregisterListener(this);		
    }


    public void reset() {
        initialized = false;		
    }


    public void activate(boolean isChecked) {
        if (isChecked) {
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            mSensorManager.unregisterListener(this, mAccelerometer);
        }
    }
}
