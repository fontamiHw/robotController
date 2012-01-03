package network.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import android.app.Activity;
import android.filedb.IpFile;

public class IpNodes {
	private ArrayList<String> allIp = new ArrayList<String>();
	private ArrayList<String> allPort = new ArrayList<String>();
	private IpFile db;
	private static IpNodes _instance;

	private String connectIp = "";
	private String connectPort = "";

	private IpNodes() {
	}
	
	public static IpNodes getInstace() {
		if (_instance == null) {
			_instance = new IpNodes();
		}
		return _instance;
	}

	private void fillData() {

		if (db != null) {

			try {
				db.open(IpFile.FILENAME, true);
				if (db != null) {
					StringBuffer fields = db.read();

					if (fields.length() != 0) {				
						getStoredData(fields);
					} 
				}
				db.close();
			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
				
			}
		}
	}
	
	
	private void getStoredData(StringBuffer fields) {
		ArrayList<String> list = new ArrayList<String>();
		StringTokenizer mainTokens, tokens;
		int fsm=0, fsm2=0;

		mainTokens = new StringTokenizer(fields.toString(), IpFile.FIELD_SEPARATOR);
		while (mainTokens.hasMoreElements()) {
			tokens = new StringTokenizer((String) mainTokens.nextElement(), IpFile.SEPARATOR);
			while (tokens.hasMoreElements()) {
				switch (fsm) {
				case 0:
					allIp.add((String) tokens.nextElement());
				break;
				
				case 1:
					allPort.add((String) tokens.nextElement());					
				break;
				
				case 2:
					if (fsm2==0) {
						connectIp =(String) tokens.nextElement();
					}
					else {
						connectPort = ((String) tokens.nextElement());
					}
					fsm2++;
				break;
				
				default:
					break;
				}
			}	
			fsm++;
		}
	}

	public String[] getConfiguratedIp() {
		return allIp.toArray(new String[0]);
	}

	public String[] getConfiguratedPort() {
		return allPort.toArray(new String[0]);
	}
	
	
	public String getConnectIp() {
		return connectIp;
	}

	public String getConnectPort() {
		return connectPort;
	}
	
	private void updateIp(String string, boolean add) throws IOException {
		connectIp = string;		
		
		if (add)
			allIp.add(string);
	}
	
	private void updatePort(String string, boolean add) throws IOException {
		connectPort = string;
		
		if (add)
			allPort.add(string);		
		
	}

	public void init(Activity activity) {
		if (db == null) {
			db = new IpFile(activity);
			fillData(); // loads the already exist Ip
		}
	}

	public void remove(String ip, String port) {
		allIp.remove(ip);
		allPort.remove(port);
	}
	
	public void update(String ip, String port, boolean add) throws IOException {
		updateIp(ip, add);	
		updatePort(port, add);
	}

	public void destroy() throws IOException {
		db.open(IpFile.FILENAME, false);
		for (Iterator<String> ipIter = allIp.iterator(); ipIter.hasNext();) {
			
			String data= ipIter.next();
			if (ipIter.hasNext())
				data += IpFile.SEPARATOR;
			
			db.write(data);
		}
		
		db.write(IpFile.FIELD_SEPARATOR);
		for (Iterator<String> ipIter = allPort.iterator(); ipIter.hasNext();) {
			
			String data= ipIter.next();
			if (ipIter.hasNext())
				data += IpFile.SEPARATOR;
			
			db.write(data);
		}
		
		db.write(IpFile.FIELD_SEPARATOR);
		db.write(connectIp+IpFile.SEPARATOR);
		db.write(connectPort+IpFile.SEPARATOR);
		
		db.close();		
	}
}
