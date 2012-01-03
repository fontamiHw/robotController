package robot.controller.menu;

import android.R.drawable;
import mf.main.R;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MyMenu {
    private Activity mRootActivity;
    
    public MyMenu(Activity mRootActivity) {
		this.mRootActivity = mRootActivity;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = mRootActivity.getMenuInflater();
            menuInflater.inflate(R.menu.robotcontrollermenu, menu);
            return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
            switch(item.getItemId()) {

            case R.id.item1:
            	return true;
            	
            	default:
            		return false;
            }
    }
}
