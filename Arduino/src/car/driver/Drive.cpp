/*
 * enginee.cpp
 *
 *  Created on: Nov 10, 2011
 *      Author: mfontane
 */

#include "drive.h"

Drive::Drive(int fPin, int bPin)
{
	realHw = new Arduino();
	forwardPin = fPin;
	realHw->setupPinOut(fPin);

	backwardPin = bPin;
	realHw->setupPinOut(bPin);
}

Drive::~Drive() {
}

long Drive::limit(long value, long fromMin, long fromMax, long toMin, long toMax)

{
	return realHw->limit(value, fromMin, fromMax, toMin, toMax);
}

long Drive::range(long  value, long  toMin, long  toMax)
{
	return realHw->range(value, toMin, toMax);
}

void Drive::analogPinWrite(int pin, int value)
{
	realHw->analogPinWrite(pin, value);
}





