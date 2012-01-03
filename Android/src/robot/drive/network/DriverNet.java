package robot.drive.network;
/* From http://java.sun.com/docs/books/tutorial/index.html */

/*
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import robot.RobotConfiguration;
import robot.drive.IDrive;
import android.os.Bundle;

public class DriverNet  implements IDrive {

    protected static IDrive _instance;
    Socket echoSocket = null;
    PrintWriter out = null;
    InputStream in = null;
    private boolean init= false;
    private String currentDirection="F";
    private int currentSpeed=0;

    public static IDrive getInstance() {
        if (_instance == null)
            _instance = new DriverNet();
        return _instance;	
    }	

    
    public void configDriver(Object confData) 
            throws NumberFormatException, UnknownHostException, IOException {
        if (!init) {
            Bundle connectionData = (Bundle) confData;

            String ip = (String) connectionData.get(RobotConfiguration.IP);
            String port = (String) connectionData.get(RobotConfiguration.PORT);


            echoSocket = new Socket(ip, Integer.parseInt(port));
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            init = true;
        }
    }


    public InputStream getIn() throws IOException {
        return echoSocket.getInputStream();
    }


    
    public void increaseSpeed(int delta) {
        out.println("+" + delta);
        currentSpeed++;
    }
    
    
    public void decreaseSpeed(int delta) {
        out.println("-" + delta);
        currentSpeed--;
    }

    
    public void rigth(int value) {
        out.println(">");
    }

    
    public void left(int value) {
        out.println("<");
    }

    
    public void setBackward() {
        if (!"B".equals(currentDirection)) {
            currentDirection = "B";
            out.println(currentDirection);
        }
    }

    
    public void setForward() {
        if (!"F".equals(currentDirection)) {
            currentDirection = "F";
            out.println(currentDirection);
        }
    }

    
    public void popOff(boolean on) {
        if (on)
            out.println("P");
        else
            out.println("P");
    }

    
    public void speed(int position, int vActualPos) {
        int goOn = position-vActualPos;
        int delta = Math.abs(goOn);
        if ("F".equals(currentDirection)) {
            if (delta > 0){
                if (goOn > 0 ){
                    increaseSpeed(delta);
                } else {
                    decreaseSpeed(delta);
                }
            }        
        } else {
            if (delta > 0){
                if (goOn < 0 ){
                    increaseSpeed(delta);
                } else {
                    decreaseSpeed(delta);
                }
            }        
        }
    }

    
    public void resetSpeed() {
        if (currentSpeed > 0) {
            for (int i=currentSpeed; i>0; i--) {
                decreaseSpeed(1);
            }
        }
    }

    
    public void dispose() throws IOException {
        echoSocket.close();
        in.close();
        out.close();
    }
}