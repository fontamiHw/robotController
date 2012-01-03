/*
 * speed.h
 *
 *  Created on: Nov 10, 2011
 *      Author: mfontane
 */

#ifndef SPEED_H_
#define SPEED_H_

#include "driver/drive.h"
#include "../DebugPrint.h"
#include "../Type.h"


class Speed: public Drive {

private:
	int commandSpeed;
	int realSpeed;
	int maxSpeed, minSpeed;
	int FBPin;

	Boolean popOff ;
	char *popOffStr;
	DebugPrint *debug;


public:
    static const int FORWARD = 3;
    static const int BACKWARD = 5;
	Speed(int fPin, int bPin, DebugPrint *debug);
	virtual ~Speed();

	int up();
	int down();
	void go();
	void setForward();
	void setBackward();
	char* changePopOff();
    int getActualSpeed() const;
	Boolean isForward() {
		return (FBPin == getForwardPin());
	}

};

#endif /* SPEED_H_ */
