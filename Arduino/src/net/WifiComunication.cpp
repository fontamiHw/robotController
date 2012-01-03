/*
 * ComunicationChannel.cpp
 *
 *  Created on: Nov 11, 2011
 *      Author: mfontane
 */

#include "WifiComunication.h"

WifiComunication::WifiComunication(int baud):arduinoDebug(baud) {
}

WifiComunication::~WifiComunication() {
}

Boolean WifiComunication::ready()
{
	return arduinoDebug.ready();
}

char WifiComunication::read()
{
	return arduinoDebug.read();
}



