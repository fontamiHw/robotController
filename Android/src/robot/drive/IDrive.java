package robot.drive;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

public interface IDrive {
	
	public  void configDriver(Object confData) throws NumberFormatException, UnknownHostException, IOException ;
	
	public InputStream getIn() throws IOException;
	
	public void rigth(int value);
	public void left(int value);

	public void popOff(boolean on);

    public void setBackward();
    public void setForward();
    public void increaseSpeed(int delta);
    public void decreaseSpeed(int delta);

    public void speed(int position, int vActualPos);

    public void resetSpeed();
    
    public void dispose()throws IOException;
}
