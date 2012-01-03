package android.filedb;

import java.io.IOException;

import android.app.Activity;

public class IpFile extends AndroidFile {

	private static IpFile _instance;
	private static final String DEFAULT_LABEL = "DEFAULT:";
	public static final String FILENAME = "ipAddress";
	public static final String SEPARATOR = ",";
	public static final String FIELD_SEPARATOR = ";";


	public IpFile(Activity ipNodesView) {
		super(ipNodesView);
	}



	public static void destroy() throws IOException {
		if (_instance != null) {
			_instance.close();
		}
	}

	public void writeDefault(String string) throws IOException {
		String data = DEFAULT_LABEL;
		write(data+string);
	}

	public StringBuffer read() {
		return super.read();
	}

}
