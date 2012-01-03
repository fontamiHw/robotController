package android.filedb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;

public abstract class AndroidFileWrite {

	private FileWriter fileOut;
	private FileReader fileIn;
	private String filename;
	private Activity activity;

	protected AndroidFileWrite(Activity activity) {	
		this.activity = activity;
	}
		
	public void open(String filename, boolean read) throws IOException {
		this.filename = "\\sdcard\\"+filename;
		if (read)
			fileIn = new FileReader(this.filename);
		else {			
			fileOut = new FileWriter(this.filename);
		}

	}
	
	public void write(String value) throws IOException {
		if (fileOut == null) {
			return;
		}		
		fileOut.write(value);
		fileOut.flush();
	}
	
	public StringBuffer read() {

		StringBuffer ret = new StringBuffer();
		int offset =0, count =20, read=1;
		char buffer[];
		
		try {
			if (fileIn == null) {
				open(filename, true);
			}
			buffer = new char[20];
			while ((read != -1) && (read !=0)){
					try {
						read = fileIn.read(buffer, offset, 20);
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
