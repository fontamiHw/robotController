/*
 * DebugPrint.cpp
 *
 *  Created on: Nov 11, 2011
 *      Author: mfontane
 */

#include "DebugPrint.h"

DebugPrint::DebugPrint(int baud):arduinoDebug(baud) {
	debugPrint = false;
}

DebugPrint::~DebugPrint() {
}

void DebugPrint::dbgPrint(char *string) {
	if (debugPrint)
		arduinoDebug.dbgPrint(string);
}

void DebugPrint::dbgPrintln(char *string) {
	if (debugPrint)
		arduinoDebug.dbgPrintln(string);
}

void DebugPrint::dbgPrintln(int value)
{
	if (debugPrint)
		arduinoDebug.dbgPrintln(value);
}

Boolean DebugPrint::ready()
{
	return arduinoDebug.ready();
}

void DebugPrint::dbgPrintln(Boolean value)
{
	if (debugPrint)
		arduinoDebug.dbgPrintln(value);
}


void DebugPrint::dbgPrint(int value)
{
	if (debugPrint)
		arduinoDebug.dbgPrint(value);
}



void DebugPrint::dbgPrint(Boolean value)
{
	if (debugPrint)
		arduinoDebug.dbgPrint(value);
}
