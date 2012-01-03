################################################################################
# Automatically-generated file. Do not edit!
################################################################################

-include ../makefile.init

RM := rm -rf

# All of the sources participating in the build are defined here
-include sources.mk
-include src/net/subdir.mk
-include src/glcd/subdir.mk
-include src/dashBoard/dial/speedMeter/clock/subdir.mk
-include src/dashBoard/dial/speedMeter/subdir.mk
-include src/dashBoard/dial/subdir.mk
-include src/dashBoard/subdir.mk
-include src/car/driver/subdir.mk
-include src/car/subdir.mk
-include src/Time/subdir.mk
-include src/HW/subdir.mk
-include src/subdir.mk
-include subdir.mk
-include objects.mk

ifneq ($(MAKECMDGOALS),clean)
ifneq ($(strip $(C++_DEPS)),)
-include $(C++_DEPS)
endif
ifneq ($(strip $(C_DEPS)),)
-include $(C_DEPS)
endif
ifneq ($(strip $(ASM_DEPS)),)
-include $(ASM_DEPS)
endif
ifneq ($(strip $(CC_DEPS)),)
-include $(CC_DEPS)
endif
ifneq ($(strip $(CPP_DEPS)),)
-include $(CPP_DEPS)
endif
ifneq ($(strip $(S_DEPS)),)
-include $(S_DEPS)
endif
ifneq ($(strip $(CXX_DEPS)),)
-include $(CXX_DEPS)
endif
ifneq ($(strip $(C_UPPER_DEPS)),)
-include $(C_UPPER_DEPS)
endif
ifneq ($(strip $(S_UPPER_DEPS)),)
-include $(S_UPPER_DEPS)
endif
endif

-include ../makefile.defs

# Add inputs and outputs from these tool invocations to the build variables 
LSS += \
ArduinoRobotController.lss \

FLASH_IMAGE += \
ArduinoRobotController.hex \

SIZEDUMMY += \
sizedummy \


# All Target
all: ArduinoRobotController.elf secondary-outputs

# Tool invocations
ArduinoRobotController.elf: $(OBJS) $(USER_OBJS)
	@echo 'Building target: $@'
	@echo 'Invoking: AVR C++ Linker'
	avr-gcc --cref -s -Os -o"ArduinoRobotController.elf" $(OBJS) $(USER_OBJS) $(LIBS) -lm -Wl,-Map,ArduinoRobotController.map,--cref -Wl,--gc-sections -L"E:\Win\hw\arduino-0022\libCore" -mmcu=atmega2560
	@echo 'Finished building target: $@'
	@echo ' '

ArduinoRobotController.lss: ArduinoRobotController.elf
	@echo 'Invoking: AVR Create Extended Listing'
	-avr-objdump -h -S ArduinoRobotController.elf  >"ArduinoRobotController.lss"
	@echo 'Finished building: $@'
	@echo ' '

ArduinoRobotController.hex: ArduinoRobotController.elf
	@echo 'Create Flash image (ihex format)'
	-avr-objcopy -R .eeprom -O ihex ArduinoRobotController.elf  "ArduinoRobotController.hex"
	@echo 'Finished building: $@'
	@echo ' '

sizedummy: ArduinoRobotController.elf
	@echo 'Invoking: Print Size'
	-avr-size --format=avr --mcu=atmega2560 ArduinoRobotController.elf
	@echo 'Finished building: $@'
	@echo ' '

# Other Targets
clean:
	-$(RM) $(OBJS)$(C_DEPS)$(ELFS)$(FLASH_IMAGE)$(LSS)$(S_DEPS)$(CXX_DEPS)$(S_UPPER_DEPS)$(C++_DEPS)$(ASM_DEPS)$(CC_DEPS)$(CPP_DEPS)$(C_UPPER_DEPS)$(SIZEDUMMY) ArduinoRobotController.elf
	-@echo ' '

secondary-outputs: $(LSS) $(FLASH_IMAGE) $(SIZEDUMMY)

.PHONY: all clean dependents
.SECONDARY:

-include ../makefile.targets
