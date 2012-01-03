/*
 * CarController.h
 *
 *  Created on: Nov 11, 2011
 *      Author: mfontane
 */

#ifndef CARCONTROLLER_H_
#define CARCONTROLLER_H_

#include "../DebugPrint.h"
#include "speed.h"
#include "direction.h"
#include "../net/WifiComunication.h"
#include "Proximity.h"
#include "Dashboard.h"

class CarController {

private:
    Dashboard  dashboard;
	DebugPrint debug;
	Direction steer;
	Speed 	  speed;
	WifiComunication channel;
	Proximity proxSensor;



public:
	CarController();
	CarController(int baud);
	virtual ~CarController();

	void commandParse();
	void executeCommand();
	void controllerSetup();

private:
	void farFromObstacle();
	void setSpeed(){
		speed.go();
	}

	void setDirection() {
		steer.go();
	}

};

#endif /* CARCONTROLLER_H_ */
