/*
 * Serial.cpp
 *
 *  Created on: Nov 11, 2011
 *      Author: mfontane
 */

#include "SerialArduino.h"
#include <WProgram.h>

extern HardwareSerial Serial;
SerialArduino::SerialArduino() {
	init(57600);
}

SerialArduino::SerialArduino(int baud) {
	init(baud);
}
void SerialArduino::init(int baud) {
	Serial.begin(115200);
		delay(100);
}
SerialArduino::~SerialArduino() {
}

void SerialArduino::dbgPrint(char *string)
{
	Serial.print(string);
}

void SerialArduino::dbgPrintln(char *string)
{
	Serial.println(string);
}

void SerialArduino::dbgPrint(int value)
{
	Serial.print(value);
}

Boolean SerialArduino::ready()
{
	return (Serial.available()>0);
}

char SerialArduino::read()
{
	return Serial.read();
}

void SerialArduino::dbgPrintln(int value)
{
	Serial.println(value);
}
