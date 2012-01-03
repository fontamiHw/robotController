/*
 * CarController.cpp
 *
 *  Created on: Nov 11, 2011
 *      Author: mfontane
 */

#include "CarController.h"

CarController::CarController(int baud):
debug(baud),
steer(Direction::RIGTH, Direction::LEFT, &debug),
speed(Speed::FORWARD, Speed::BACKWARD, &debug),
channel(baud),
proxSensor(&speed, &debug),
dashboard(GLCD.CenterY-4){


	debug.setOn(true);
	debug.dbgPrintln("Press any key to continue");
	while(!debug.ready());
	debug.dbgPrintln("Press + to Increase the PWM (Pulse-width modulation)");
	debug.dbgPrintln("Press - to Decrease the PWM (Pulse-width modulation)");
	debug.dbgPrintln("Press F to goes Forward");
	debug.dbgPrintln("Press B to goes Backward");
	debug.dbgPrintln("Press P to Activate the limiter");
	debug.dbgPrintln("");
	debug.dbgPrintln("Press < to Steer Left");
	debug.dbgPrintln("Press > to Steer Right");
	debug.dbgPrintln("Enter: ");
	dashboard.init();
}

CarController::~CarController() {
}


void CarController::commandParse() {

	char input='%';

	debug.setOn(false);
	if (channel.ready()){
		input = channel.read();
	}

	switch (input){

	case '+':
		debug.setOn(true);
		speed.up();
		break;

	case '-':
		debug.setOn(true);
		speed.down();
		break;

	case '>':
		debug.setOn(true);
		steer.rigth();
		if (steer.isSteering())
		    dashboard.arrowRigthOn(true);
		else
		    dashboard.arrowLeftOn(false);
		break;

	case '<':
		debug.setOn(true);
		steer.left();
        if (steer.isSteering())
            dashboard.arrowLeftOn(true);
        else
            dashboard.arrowRigthOn(false);
		break;

	case 'F':
		speed.setForward();
		break;

	case 'B':
		speed.setBackward();
		break;

	case 'P':
		speed.changePopOff();
		debug.setOn(true);
		break;

	default:
		debug.setOn(true);
		break;
	}

//	proxSensor.farFromObstacle();
	debug.setOn(false);

	//update Clock
	dashboard.setSpeed(speed.getActualSpeed(), speed.isForward());
	dashboard.update();
}

void CarController::executeCommand(){
	setSpeed();
	setDirection();
}

