package network.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import mf.main.R;
import robot.RobotConfiguration;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class EnterIp extends Activity {

	private static final String TOKEN_SEPARATOR = "-";
	private EditText ipAddr, port;
	private IpNodes db;
	private Button removeIpButton;
	private boolean update=true;
	private Intent myIntent;
	private ArrayAdapter<String> listIp;
	private ListView menuListView;
	protected String selectedItem;

	@Override
	protected void onDestroy() {
		try {
			if (update)
				db.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.onDestroy();
	}


	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.enterip);

		ipAddr = (EditText) findViewById(R.id.IpText);
		port = (EditText) findViewById(R.id.portText);
		port.setText("4468");
		Button connectButton = (Button) findViewById(R.id.Connect);        
		connectButton.setOnClickListener(connectListener);

		removeIpButton = (Button) findViewById(R.id.removeIp);
		removeIpButton.setOnClickListener(removeIpListener);

		 menuListView = (ListView) findViewById(R.id.ipList);

		db = IpNodes.getInstace();
		db.init(this);
		String[] ips = db.getConfiguratedIp();
		String[] ports = db.getConfiguratedPort();
		ArrayList<String> items = new ArrayList<String>();
		for (int i = 0; i < ips.length; i++) {
			items.add(ips[i] + TOKEN_SEPARATOR + ports[i]);
		}
		listIp = new ArrayAdapter<String>(this, R.layout.menu_item, items);
		menuListView.setAdapter(listIp);

		menuListView.setOnItemClickListener(listListener);
	}

	private void addExtraIntentData() throws IOException {
		if (myIntent == null)
			myIntent = new Intent(EnterIp.this, RobotConfiguration.class);


		myIntent.putExtra(RobotConfiguration.IP, ipAddr.getText().toString());
		myIntent.putExtra(RobotConfiguration.PORT, port.getText().toString());	    
		EnterIp.this.finish();
	}

	View.OnClickListener connectListener = new OnClickListener() {
		public void onClick(View v) {			
			
			newData(true);
			try {
				addExtraIntentData();

				if (myIntent!= null)
					EnterIp.this.startActivity(myIntent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	};
	
	View.OnClickListener removeIpListener = new OnClickListener() {
		public void onClick(View v) {
			listIp.remove(selectedItem);
			listIp.notifyDataSetChanged();
			
			newData(false);			
			removeIpButton.setVisibility(View.INVISIBLE);
			update = true;
		}		
	};
	

	private void newData(boolean add) {
		Editable value = ipAddr.getText(); 
		Editable portValue = port.getText();

		try {
			if (db != null) {
				if (add) {
					db.update(value.toString(), portValue.toString(), update);
				}
				else
					db.remove(value.toString(), portValue.toString());

			}		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private OnItemClickListener listListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> list, View view,
				int position, long id) {
			update = false;
			

			
			selectedItem = ((TextView) view).getText().toString();
			configureIpAndPort();
			removeIpButton.setVisibility(View.VISIBLE);
		}

		private void configureIpAndPort() {
			StringTokenizer strToken = new StringTokenizer(selectedItem, TOKEN_SEPARATOR);
			if(strToken.hasMoreElements()) {
				String newIp = strToken.nextToken();
				ipAddr.setText(newIp);
				update |= newIp.equals(db.getConnectIp());
			}
			if(strToken.hasMoreElements()) {
				String newPort = strToken.nextToken();
				port.setText(newPort);
				update |= newPort.equals(db.getConnectPort());
			}
		}
	};

}