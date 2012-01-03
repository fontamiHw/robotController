package mf.main;

import java.io.IOException;

import network.configuration.EnterIp;
import network.configuration.IpNodes;
import robot.RobotConfiguration;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Main extends Activity {
	private IpNodes db;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        
        
        db = IpNodes.getInstace();
        db.init(this);
        
        Intent startInt = new Intent(this, RobotConfiguration.class);
        startInt.putExtra(RobotConfiguration.IP, db.getConnectIp());
        startInt.putExtra(RobotConfiguration.PORT, db.getConnectPort());	   
        startActivity(startInt);
        
        Main.this.finish();
    }	
}
