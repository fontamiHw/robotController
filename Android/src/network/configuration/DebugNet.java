package network.configuration;

public class DebugNet {
	private static DebugNet _instance;
	private String ip;
	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	private int port;
	
	public static DebugNet getInstance(){
		if (_instance == null) {
			_instance = new DebugNet();
		}
		return _instance;
	}
	
	public String getIp(){
		return ip;
	}
	
	public int getPort() {
		return port;
	}
}
