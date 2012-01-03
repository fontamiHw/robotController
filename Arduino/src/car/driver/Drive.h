/*
 * enginee.h
 *
 *  Created on: Nov 10, 2011
 *      Author: mfontane
 */

#ifndef DRIVE_H_
#define DRIVE_H_
#include "../../HW/Arduino.h"


class Drive {
private:
	int forwardPin;
	int backwardPin;
	Arduino *realHw;

public:
	Drive(){};
	Drive(int fPin, int bPin);
	virtual ~Drive();

	long limit(long value, long fromMin, long fromMax, long toMin, long toMax);
	long range(long value, long toMin, long toMax);

	inline void sleep(unsigned long milliSec){realHw->sleep(milliSec);};

    inline int getBackwardPin() const
    {
        return backwardPin;
    }

    inline int getForwardPin() const
    {
        return forwardPin;
    }
	void analogPinWrite(int pin, int value);

};

#endif /* ENGINEE_H_ */
