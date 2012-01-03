package robot;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Observable;

import robot.controller.RobotControllerNotify;
import robot.drive.IDrive;
import robot.drive.network.DriverNet;
import android.os.Bundle;

public class RobotMove extends Observable  {

    private IDrive driver;
    private int hActualPos=0, vActualPos=0;
    private int referenceX;
    private int referenceY;

    public RobotMove() {
        super();
        driver = DriverNet.getInstance();
    }

    public void configDriver(Bundle dataConfigration) throws RobotException {
        if (driver != null) {
            try {
                driver.configDriver(dataConfigration);
            } catch (NumberFormatException e) {
                throw new RobotException("Wrong PORT value");
            } catch (UnknownHostException e) {
                throw new RobotException("Wrong IP configuration");
            } catch (IOException e) {
                throw new RobotException("General IO Error");
            }
        } else
            throw new RobotException("Driver not Instatiated");
    }

    public void horizzontal(int position) {
        int deltax = (int) (position - hActualPos);
        RobotControllerNotify notify=null;
        if (IRobotMove.FILTER_HORIZZONTAL < Math.abs(deltax)) {

            if (deltax <0)
                driver.left(hActualPos - position);
            else 
                driver.rigth(position - hActualPos);

            hActualPos = position;

            setChanged();
            notify = new RobotControllerNotify(RobotControllerNotify.CHANGE_X);
            notify.setData(hActualPos*10);


            notifyObservers(notify);
        }
    }

    public void vertical(int position) {
        RobotControllerNotify notify=null;

        int changes = position - referenceY;
        setdirection(changes);
        
        driver.speed(position, vActualPos);
        
        vActualPos = position;

        setChanged();           
        notify = new RobotControllerNotify(RobotControllerNotify.CHANGE_Y);
        notify.setData(vActualPos*10);
    }

    private void setdirection(int direction) {
        if (direction > 0)
            driver.setForward();
        else if (direction < 0)
            driver.setBackward();        
    }

    public IDrive getDriver() {
        return driver;
    }

    public void popOff(boolean on) {
        driver.popOff(on);
    }

    public Boolean initialization(int mSensorX, int mSensorY) {
        referenceX = mSensorX;
        referenceY = mSensorY;
        driver.setForward();
        driver.resetSpeed();
        return true;
    }	
    
    public void dispose() throws IOException {
        driver.dispose();
    }
}
