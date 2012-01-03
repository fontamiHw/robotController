/*
 * memory.h
 *
 *  Created on: Nov 10, 2011
 *      Author: mfontane
 */

#ifndef MEMORY_H_
#define MEMORY_H_
#include <WProgram.h>

void * operator new(size_t size);
void operator delete(void * ptr);

void * operator new[](size_t size);

void operator delete[](void * ptr);

#endif /* MEMORY_H_ */
