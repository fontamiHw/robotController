/*
 * ComunicationChannel.h
 *
 *  Created on: Nov 11, 2011
 *      Author: mfontane
 */

#ifndef COMUNICATIONCHANNEL_H_
#define COMUNICATIONCHANNEL_H_
#include "../HW/SerialArduino.h"
#include "../Type.h"

class WifiComunication {
private:
	SerialArduino arduinoDebug;

public:
	WifiComunication(int baud);
	virtual ~WifiComunication();

	Boolean ready();
	char read();
};

#endif /* COMUNICATIONCHANNEL_H_ */
