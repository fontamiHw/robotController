package robot.controller;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import mf.main.R;
import robot.RobotException;
import robot.RobotMove;
import robot.controller.menu.MyMenu;
import robot.moving.RobotAccelerometer;
import robot.moving.network.RobotNetwork;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ToggleButton;

public class RobotController extends Activity 
implements Observer {

	private static final int HORIZZONTAL_OFFSET = 50;
	private boolean initialized=false;
	private SeekBar verticalSeek;
	private SeekBar horizzontalSeek;

	private RobotAccelerometer robotSensorOut;
	private RobotMove robotDriver;
	private MyMenu mMyMenu;
	private RobotNetwork robotSensorIn;

    protected PowerManager.WakeLock mWakeLock;
	
	public RobotController() {
		robotDriver = new RobotMove();	
	}

    
    protected void onDestroy() {
        super.onDestroy();
        this.mWakeLock.release();
//        this.setRequestedOrientation(
//                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        
        if (robotSensorOut!= null) {
            robotSensorOut.destroy();
            try {
                robotSensorIn.destroy();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if (robotDriver != null) {
            try {
                robotDriver.dispose();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    
	
	protected void onRestart() {
		initialized=false;
		super.onRestart();
	}	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialized=false;
        mMyMenu = new MyMenu(this);

		setContentView(R.layout.robotcontroller);
		
		verticalSeek = (SeekBar) findViewById(R.id.seekVertical);		
		verticalSeek.setOnSeekBarChangeListener(vSeekBarListener);
		verticalSeek.setVisibility(View.INVISIBLE);
		
		horizzontalSeek = (SeekBar) findViewById(R.id.seekHorizzontal);		
		horizzontalSeek.setOnSeekBarChangeListener(hSeekBarListener);
		horizzontalSeek.setVisibility(View.INVISIBLE);
		
		ToggleButton sensorButton = (ToggleButton)findViewById(R.id.sensorButton);
		sensorButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {	
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				robotSensorOut.activate(isChecked);
			}
		});

		
		ToggleButton popOffButton = (ToggleButton)findViewById(R.id.popOffButton);
		popOffButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {	
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				robotDriver.popOff(isChecked);
			}
		});
		Button syncbutton = (Button)findViewById(R.id.syncButton);
		syncbutton.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View arg0) {
			initialized = false;
			robotSensorOut.reset();
			}
		});
		
		
		try {           
			// just the first time 
			robotDriver.configDriver(getIntent().getExtras());
			robotDriver.addObserver(this);
            robotSensorOut = new RobotAccelerometer(this, robotDriver);
			horizzontalSeek.setProgress(HORIZZONTAL_OFFSET);
//			horizzontalSeek.setVisibility(View.VISIBLE);
//			verticalSeek.setVisibility(View.VISIBLE);
			
			
			// set the input from robot
            robotSensorIn = new RobotNetwork(robotDriver.getDriver().getIn());
            
			
		} catch (RobotException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        
//        this.setRequestedOrientation(
//                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
		/* This code together with the one in onDestroy()
         * will make the screen be always on until this Activity gets destroyed. */
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        this.mWakeLock.acquire();

	}
	
	
    public boolean onCreateOptionsMenu(Menu menu) {
            mMyMenu.onCreateOptionsMenu(menu);
            return super.onCreateOptionsMenu(menu);
    }

    
    public boolean onOptionsItemSelected(MenuItem item) {
            mMyMenu.onOptionsItemSelected(item);
            return super.onOptionsItemSelected(item);
    }
	
	
	private OnSeekBarChangeListener hSeekBarListener = new OnSeekBarChangeListener() {


        
		public void onStopTrackingTouch(SeekBar arg0) {
		}

		
		public void onStartTrackingTouch(SeekBar arg0) {

		}

		
		public void onProgressChanged(SeekBar bar, int position, boolean fromUser) {
		    if (initialized && fromUser) {
		        robotDriver.horizzontal(position);
		    }
		}
	};
	
	private OnSeekBarChangeListener vSeekBarListener = new OnSeekBarChangeListener() {

		
		public void onStopTrackingTouch(SeekBar arg0) {

		}

		
		public void onStartTrackingTouch(SeekBar arg0) {

		}

		
		public void onProgressChanged(SeekBar bar, int position, boolean fromUser) {
			if (initialized && fromUser) {
				robotDriver.vertical(position);
			}
		}
	};


	
	public void update(Observable arg0, Object argument) {
		if (argument instanceof RobotControllerNotify) {
			RobotControllerNotify notif = ((RobotControllerNotify)argument);
			switch (notif.getType()) {
			case RobotControllerNotify.INITIALIZE:
				initialized = true;	
				break;

			case RobotControllerNotify.CHANGE_X:
				horizzontalSeek.setProgress(HORIZZONTAL_OFFSET-(notif.getData()-HORIZZONTAL_OFFSET));
				break;
				
			case RobotControllerNotify.CHANGE_Y:
				verticalSeek.setProgress(notif.getData());
				break;
				
			default:
				break;
			}			
		}
	}
	
	
	
}
