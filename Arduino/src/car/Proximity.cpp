/*
 * Proximity.cpp
 *
 *  Created on: Nov 14, 2011
 *      Author: mfontane
 */

#include "Proximity.h"

Proximity::Proximity(Speed *_speed, int analogPin, DebugPrint *dbg) : Sensor(analogPin){
	init(_speed, dbg);
}

Proximity::Proximity(Speed *_speed, DebugPrint *dbg) : Sensor(0){
	init(_speed, dbg);
}

void Proximity::init(Speed *_speed, DebugPrint *dbg) {
	speed = _speed;
	oldSample = oldDist = dist = _8M;
	debug = dbg;
}

Proximity::~Proximity() {
}

//static const int _INFINITYM = 70;
//static const int _8M = 110;
//static const int _5M = 150;
//static const int _3M = 280;
//static const int _0M = 600;

int Proximity::getDistance() {
	int anlValue =  getValue();

	if ((anlValue < _8M))
		return _8M;
	if ((anlValue < _5M) && (anlValue > _8M))
		return _5M;
	else if ((anlValue < _3M) && (anlValue > _5M))
		return _3M;
	else
		return _0M;

}



/*
 * it return true if is far for any obstacle and the speed
 * reflect the user selection.
 * Otherwise:
 * from _5M to _3M reduce the speed with the forward direction.
 * at _0M set the backward direction
 */
void Proximity::farFromObstacle(){

	average();

	if ((samplesDist == MAX_SAMPLES) && (oldDist != dist)) {
		Boolean back;
		back = goingBack();

		debug->dbgPrint("Old obstacle = ");
		debug->dbgPrintln(oldDist);
		debug->dbgPrint("Obstacle = ");
		debug->dbgPrint(dist);
		debug->dbgPrint("-");


		oldDist = dist;
		if (back)
			return;

		switch (dist) {

		// too closer....decrease speed
		// if the direction is forward
		case _5M:
			debug->dbgPrintln("_5M");
		case _3M:
			if (dist != _5M)
				debug->dbgPrintln("_3M");

			if (speed->isForward()) {
				debug->setOn(true);
				speed->down();
			}
			break;

		case _0M:
			debug->dbgPrintln("_0M");
			if (speed->isForward()) {
				debug->setOn(true);
				speed->setBackward();//force the back direction, do not set the user request
			}
			break;


		case _8M:
			debug->dbgPrintln("_8M");
		default:
			break;
		}
	}
}

void Proximity::average() {
    int newSample;
	newSample = getDistance();

	if (oldSample == newSample) {
		//	distance is same just increase the samples counter
//		debug->dbgPrint("Old obstacle = ");
//		debug->dbgPrintln(oldSample);
//		debug->dbgPrint(" Obstacle = ");
//		debug->dbgPrint(newSample);

		if (samplesDist < SENT_COMMAND) {
			samplesDist++;
			if (samplesDist == MAX_SAMPLES)
				dist = newSample;
		}

//		debug->dbgPrint(" samplesDist = ");
//		debug->dbgPrintln(samplesDist);
	}else  if (newSample < oldSample) {
		// far from the obstacle, compared with the latest time
		// reset the counter
		oldSample = newSample;
//		debug->dbgPrint("Distance INCREASE");
//		debug->dbgPrint("Old obstacle = ");
//		debug->dbgPrintln(oldSample);
//		debug->dbgPrint(" Obstacle = ");
//		debug->dbgPrintln (newSample);

		samplesDist=0;
	} else {
//		debug->dbgPrint("Distance DECREASE");
//		debug->dbgPrint("Old obstacle = ");
//		debug->dbgPrintln(oldSample);
//		debug->dbgPrint(" Obstacle = ");
//		debug->dbgPrint(newSample);
		oldSample = newSample;
		samplesDist=0;
//		debug->dbgPrint(" new Old obstacle = ");
//		debug->dbgPrintln(oldSample);
//		debug->dbgPrintln("");
	}
}
