/*
 * Proximity.h
 *
 *  Created on: Nov 14, 2011
 *      Author: mfontane
 */

#ifndef PROXIMITY_H_
#define PROXIMITY_H_

#include "driver/Sensor.h"
#include "speed.h"


//static const int _INFINITYM = 50;
static const int _8M = 150;
static const int _5M = 280;
static const int _3M = 400;
static const int _0M = 600;

class Proximity: public Sensor {

private:
	Speed *speed;
    int dist, oldDist, oldSample;
	DebugPrint *debug;
    int samplesDist;
    static const int MAX_SAMPLES = 4;
    static const int SENT_COMMAND = MAX_SAMPLES+1;



private:
    void init(Speed *_speed, DebugPrint *dbg);
	int getDistance();
	void average();
	inline Boolean goingBack(){return (dist < oldDist);};

public:
	Proximity(Speed *speed, DebugPrint *dbg);
	Proximity(Speed *speed, int analogInput, DebugPrint *dbg);
	virtual ~Proximity();
	void farFromObstacle();

};

#endif /* PROXIMITY_H_ */
