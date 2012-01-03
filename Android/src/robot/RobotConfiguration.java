package robot;

import robot.controller.RobotController;
import mf.main.R;
import network.configuration.EnterIp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RobotConfiguration extends Activity {
	
	public static final String PORT = "Port";
	public static final String IP = "IP";
	private Button startEngineButton;
	private Button configButton;
	private String ip;
	private String port;
	 
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.robotconfiguration);

        startEngineButton = (Button) findViewById(R.id.StartButton);        
        startEngineButton.setOnClickListener(startEngineListener);
                
        configButton = (Button) findViewById(R.id.IpButton);        
        configButton.setOnClickListener(ipListener);
        checkIp();
    }
    
    private void checkIp() {
    	Bundle connectionData = getIntent().getExtras();
    	TextView defaultIp = (TextView) findViewById(R.id.TextView3);
    	configButton.setOnClickListener(ipListener);
    	
    	if (connectionData == null) {
            startEngineButton.setVisibility(View.INVISIBLE);
            defaultIp.setVisibility(View.INVISIBLE);

    	} else {
    		ip = (String) connectionData.get(IP);
    		port = (String) connectionData.get(PORT);
            
           defaultIp.setText(ip + ":" + port);
            startEngineButton.setVisibility(View.VISIBLE);
            defaultIp.setVisibility(View.VISIBLE);
    	}
	}

	View.OnClickListener startEngineListener = new OnClickListener() {
        public void onClick(View v) {
        	Intent intent = new Intent(RobotConfiguration.this, RobotController.class);
        	intent.putExtra(RobotConfiguration.IP, ip);
        	intent.putExtra(RobotConfiguration.PORT, port);	   
            startActivity(intent);
        }
    };
    
    View.OnClickListener ipListener = new OnClickListener() {
        public void onClick(View v) {
        	Intent ip = new Intent(RobotConfiguration.this, EnterIp.class);   
        	
            startActivity(ip);
        }
    };
}