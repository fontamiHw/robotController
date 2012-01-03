/*
 * Arduino.h
 *
 *  Created on: Nov 11, 2011
 *      Author: mfontane
 */

#ifndef ARDUINO_H_
#define ARDUINO_H_



class Arduino {
public:
	Arduino();
	virtual ~Arduino();

public:
	void setupPinOut(int pin);
	void setupPinIn(int pin);
	long limit(long value, long fromMin, long fromMax, long toMin, long toMax);
	long range(long value, long toMin, long toMax);

	void sleep(unsigned long milliSec);
	void analogPinWrite(int pin, int value);

	int getADCvalue(int APin);
};

#endif /* ARDUINO_H_ */
