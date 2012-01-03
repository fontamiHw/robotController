/*
 * Sensor.cpp
 *
 *  Created on: Nov 14, 2011
 *      Author: mfontane
 */

#include "Sensor.h"

Sensor::Sensor(int analogPin) {
	realHw = new Arduino();
	sensorPin = analogPin;
}

Sensor::~Sensor() {
}

int Sensor::getValue(){
	return realHw->getADCvalue(sensorPin);
}
