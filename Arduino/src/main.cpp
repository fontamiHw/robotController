#include "car/CarController.h"
#include "memory.h"
#include <WProgram.h>


CarController *controller;


void setup() {
    controller = new CarController(57600);
}


void loop() {
    controller->commandParse();
    controller->executeCommand();
}


int main(void) {

    /* Must call init for arduino to work properly */
    init();
    setup();

    for (;;) {
        loop();
    } // end for
} // end main
