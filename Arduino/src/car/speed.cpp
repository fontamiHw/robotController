/*
 * speed.cpp
 *
 *  Created on: Nov 10, 2011
 *      Author: mfontane
 */

#include "speed.h"

int Speed::up()
{
	//	if the car is far from an obstacle
	//	continue to increase the speed
		commandSpeed++;
		if (commandSpeed > maxSpeed)
			commandSpeed =maxSpeed;

	return commandSpeed;
}

int Speed::down()
{
	commandSpeed--;
	if (commandSpeed < minSpeed)
		commandSpeed = minSpeed;

	return commandSpeed;
}



void Speed::go()
{
	// map the speed to an acceptable Min and Max value for the motor
	realSpeed = limit(commandSpeed, 0, 10, 0, 255);

	// if popOff value is active .... limit the max
	if (popOff) {
		realSpeed = range(realSpeed, 0, 102);
	}

	debug->dbgPrint("Speed = ");
	debug->dbgPrint(realSpeed);
	debug->dbgPrint(" PopOff = ");
	debug->dbgPrint(popOffStr);
	debug->dbgPrint(" Pin = ");


	if (FBPin == getForwardPin())
		debug->dbgPrint(" FORWARD (");
	else  if (FBPin == getBackwardPin())
		debug->dbgPrint(" BACKWARD (");
	else
		debug->dbgPrint(" ????????? (");

	debug->dbgPrint(FBPin);
	debug->dbgPrintln(")");

	// set the value to the correct pin, according with the sense of march
	analogPinWrite(FBPin, realSpeed);
}


int Speed::getActualSpeed() const
{
    return commandSpeed;
}



char* Speed::changePopOff()
{

	popOff = !popOff;
	if (popOff) {
		popOffStr =  "TRUE";
	} else {
		popOffStr =  "FALSE";
	}

	return popOffStr;
}

Speed::Speed(int fPin, int bPin, DebugPrint *dbg):
										Drive(fPin, bPin)
{
	popOff=false;
	maxSpeed=10;
	minSpeed=0;
	commandSpeed =0;
	FBPin = getForwardPin();
	debug = dbg;

	debug->dbgPrint("StartSpeed = ");
	debug->dbgPrint(0);

	debug->dbgPrint(" PopOff = ");
	debug->dbgPrintln(popOffStr);

}

void Speed::setForward()
{
	analogPinWrite(FBPin, 0);
	sleep(100);
	FBPin = getForwardPin();
	debug->setOn(true);
}

void Speed::setBackward()
{
	analogPinWrite(FBPin, 0);
	sleep(100);
	FBPin = getBackwardPin();
	debug->setOn(true);
}

Speed::~Speed() {
}
