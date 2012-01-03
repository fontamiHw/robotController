/*
 * Net.h
 *
 *  Created on: Nov 11, 2011
 *      Author: mfontane
 */

#ifndef NET_H_
#define NET_H_
#include "../Type.h"

class SerialArduino {

private:
	void init(int baud);

public:
	SerialArduino();
	SerialArduino(int baud);
	virtual ~SerialArduino();

	Boolean ready();
	char read();
	void dbgPrint(char *string);
	void dbgPrintln(char *string);
	void dbgPrint(int value);
	void dbgPrintln(int value);
};

#endif /* NET_H_ */
