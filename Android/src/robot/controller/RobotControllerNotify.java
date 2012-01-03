package robot.controller;

public class RobotControllerNotify {

	public static final int INITIALIZE = 0;
	public static final int CHANGE_X   = 1;
	public static final int CHANGE_Y   = 2;
	private int data;
	private int type;
	
	public RobotControllerNotify(int type) {
		setType(type);		
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

}
