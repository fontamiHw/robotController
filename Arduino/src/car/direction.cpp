/*
 * direction.cpp
 *
 *  Created on: Nov 10, 2011
 *      Author: mfontane
 */

#include "direction.h"

Direction::~Direction() {
}

Direction::Direction()
{

}
Direction::Direction(int fPin, int bPin, DebugPrint *dbg): Drive(fPin, bPin){
	steering=false;
	actualDirPin = 0;
	debug =dbg;
	minDir=0;
	maxDir=51;
}

void Direction::rigth()
{
	if (!steering) {
		actualDirPin = getForwardPin();
		steering = true;
	} else if (actualDirPin == getBackwardPin()) {
		steering = false;
	} else {
		steering = true;
	}
}



void Direction::left()
{
	if (!steering) {
		actualDirPin = getBackwardPin();
		steering = true;
	} else if (actualDirPin == getForwardPin()) {
		steering = false;
	} else {
		steering = true;
	}
}

Boolean Direction::isSteering() const
{
    return steering;
}

int Direction::getActualDir() const
{
    return actualDirPin;
}


void Direction::go()
{
	int realDir;
	// map the Min and Max Value to the request limit
	realDir = limit(steering, 0, 1, minDir, maxDir);

	debug->dbgPrint("Dir = ");
	debug->dbgPrint(realDir);
	debug->dbgPrint(" Pin = ");


	if (actualDirPin == getBackwardPin())
		debug->dbgPrint("LEFT (");
	else  if (actualDirPin == getForwardPin())
		debug->dbgPrint("RIGTH (");
	else
		debug->dbgPrint(" STRAIT ON (");

	debug->dbgPrint(actualDirPin);debug->dbgPrintln(")");
	debug->dbgPrintln("");
	debug->dbgPrintln("");

	// set the value to the correct pin, according with the direction
	analogPinWrite(actualDirPin, realDir);
}





