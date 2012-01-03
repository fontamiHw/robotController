/*
 * DebugPrint.h
 *
 *  Created on: Nov 11, 2011
 *      Author: mfontane
 */

#ifndef DEBUGPRINT_H_
#define DEBUGPRINT_H_

#include "HW/SerialArduino.h"
#include "Type.h"

class DebugPrint {
private:
	Boolean debugPrint;
	SerialArduino arduinoDebug;

public:
	DebugPrint(){};
	DebugPrint(int baud);
	virtual ~DebugPrint();

	Boolean ready();
	void dbgPrint(char *string);
	void dbgPrintln(char *string);
	void dbgPrint(int value);
	void dbgPrint(Boolean value);
	void dbgPrintln(int value);
	void dbgPrintln(Boolean value);

	void setOn(Boolean on)
	{
		debugPrint = on;
	}
	Boolean getDebugPrint() const
	{
		return debugPrint;
	}

	void setDebugPrint(Boolean debugPrint)
	{
		this->debugPrint = debugPrint;
	}
};

#endif /* DEBUGPRINT_H_ */
