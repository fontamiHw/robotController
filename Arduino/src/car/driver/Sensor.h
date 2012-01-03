/*
 * Sensor.h
 *
 *  Created on: Nov 14, 2011
 *      Author: mfontane
 */

#ifndef SENSOR_H_
#define SENSOR_H_

#include "../../HW/Arduino.h"

class Sensor {
private:
	Arduino *realHw;
	int sensorPin;

public:
	Sensor(int analogPin);
	virtual ~Sensor();
	int getValue();
};

#endif /* SENSOR_H_ */
