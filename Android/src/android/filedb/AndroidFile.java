package android.filedb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;

public abstract class AndroidFile {

	private FileOutputStream fileOut;
	private FileInputStream fileIn;
	private String filename;
	private Activity activity;

	protected AndroidFile(Activity activity) {	
		this.activity = activity;
	}
		
	public void open(String filename, boolean read) throws FileNotFoundException {
		this.filename = filename;
		if (read)
			fileIn = activity.openFileInput(filename);
		else
			fileOut = activity.openFileOutput(filename, Context.MODE_WORLD_WRITEABLE);

	}
	
	public void write(String ip) throws IOException {
		if (fileOut == null) {
			open(filename, false);
		}
		fileOut.write(ip.getBytes());
		fileOut.flush();
	}
	
	public StringBuffer read() {

		StringBuffer ret = new StringBuffer();
		int offset =0, count =20, read=1;
		byte buffer[];
		
		try {
			if (fileIn == null) {
				open(filename, true);
			}
			buffer = new byte[fileIn.available()];
			while ((read != -1) && (read !=0)){
					try {
						read = fileIn.read(buffer, offset, fileIn.available());
					} catch (IndexOutOfBoundsException e) {
						break;
					}
					
					if (read > 0) 
						ret.append(new String(buffer));
					offset += read;
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public void close() throws IOException {
		if (fileOut != null) {
			fileOut.close();	
			fileOut = null;
		}

		if (fileIn != null) {
			fileIn.close();
			fileIn = null;
		}
	} 
	
}
