/*
 * Arduino.cpp
 *
 *  Created on: Nov 11, 2011
 *      Author: mfontane
 */

#include "Arduino.h"

#include <WProgram.h>

Arduino::Arduino() {
}

Arduino::~Arduino() {
}


void Arduino::setupPinOut(int pin) {
	pinMode(pin, OUTPUT);
}

void Arduino::setupPinIn(int pin) {
	pinMode(pin, INPUT);
}

long Arduino::limit(long value, long fromMin, long fromMax, long toMin, long toMax)
{
	return map(value, fromMin, fromMax, toMin, toMax);
}

long Arduino::range(long value, long toMin, long toMax)
{
	return constrain(value, toMin, toMax);
}

void Arduino::sleep(unsigned long milliSec)
{
	delay(milliSec);
}

void Arduino::analogPinWrite(int pin, int value)
{
	analogWrite(pin,  value);
}

int Arduino::getADCvalue(int APin) {
	return analogRead(A0+APin);
}


