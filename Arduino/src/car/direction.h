/*
 * direction.h
 *
 *  Created on: Nov 10, 2011
 *      Author: mfontane
 */

#ifndef DIRECTION_H_
#define DIRECTION_H_

#include "driver/drive.h"
#include "../DebugPrint.h"


class Direction: public Drive {
private:
	int actualDirPin,  maxDir, minDir;
	Boolean steering;
	DebugPrint *debug;
public:
    static const int RIGTH = 11;
    static const int LEFT = 9;
	Direction();
	Direction(int fPin, int bPin, DebugPrint *dbg);
	virtual ~Direction();


	void rigth();
	void left();
	void go();
    Boolean isSteering() const;
    int getActualDir() const;

};

#endif /* DIRECTION_H_ */
