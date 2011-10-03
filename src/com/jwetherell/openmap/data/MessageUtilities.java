package com.jwetherell.openmap.data;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


/**
 * This abstract class has many static methods to display message to the user.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public abstract class MessageUtilities {
	/**
	 * Alert the user using the Toast mechanism.
	 * @param context Context of the message.
	 * @param msg String to display.
	 */
    public static void alertUser(Context context, String msg) {
    	if (context==null || msg==null) return;
    	
        Toast t = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }
}
