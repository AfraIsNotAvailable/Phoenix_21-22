# Stuff we use & useful links

#### Am salvat ca si *ulterior reading material*

## Omni

[***Omnidirectional Research Paper***](https://www.researchgate.net/publication/308570348_Inverse_kinematic_implementation_of_four-wheels_mecanum_drive_mobile_robot_using_stepper_motors) - the main research material for the omnidirectional mecanum movement model; articolul care a facut sa existe omni-ul in preaslavit

![reference image for croi's code](/resources/model_kinematic_mecanum.PNG)

[***The Futut One***](https://research.ijcaonline.org/volume113/number3/pxc3901586.pdf) - inverse kinematics model of mecanum wheels, ceva mai greu de inteles (mult mai greu)

[***Modeling and Kinematics***](https://www.hindawi.com/journals/jr/2018/9373580/) - inca il studiez pe asta dar pe scurt ii un concept de wheelchair care foloseste un model de omni pentru miscare/omni pus in practica

[***All kinds of drivetrai/movements***](https://files.andymark.com/2008CON-Omni-Baker-McKenzie.pdf) - de citit interesting stuff

## onButtonTapped!!! ADI!!! l-am gasit -__-

![reference image for the onButtonTapped thing for multiple modes on a controller](/resources/onButtonTapped_reference.png)

---

## NOTICE

This repository contains the public FTC SDK for the Freight Frenzy (2021-2022) competition season.


### User Documentation and Tutorials
*FIRST* maintains online documentation with information and tutorials on how to use the *FIRST* Tech Challenge software and robot control system.  You can access this documentation using the following link:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FtcRobotController Online Documentation](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki)

Note that the online documentation is an "evergreen" document that is constantly being updated and edited.  It contains the most current information about the *FIRST* Tech Challenge software and control system.

### Javadoc Reference Material
The Javadoc reference documentation for the FTC SDK is now available online.  Click on the following link to view the FTC SDK Javadoc documentation as a live website:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FTC Javadoc Documentation](https://javadoc.io/org.firstinspires.ftc)

### Online User Forum
For technical questions regarding the Control System or the FTC SDK, please visit the FTC Technology forum:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FTC Technology Forum](https://ftcforum.firstinspires.org/forum/ftc-technology)


# Release Information

## Version 7.0 (20210915-141025)

### Enhancements and New Features
* Adds support for external libraries to OnBotJava and Blocks.
    * Upload .jar and .aar files in OnBotJava.
      * Known limitation - RobotController device must be running Android 7.0 or greater.
      * Known limitation - .aar files with assets are not supported.
    * External libraries can provide support for hardware devices by using the annotation in the
      com.qualcomm.robotcore.hardware.configuration.annotations package.
    * External libraries can include .so files for native code.
    * External libraries can be used from OnBotJava op modes.
    * External libraries that use the following annotations can be used from Blocks op modes.
      * org.firstinspires.ftc.robotcore.external.ExportClassToBlocks
      * org.firstinspires.ftc.robotcore.external.ExportToBlocks
    * External libraries that use the following annotations can add new hardware devices:
      * com.qualcomm.robotcore.hardware.configuration.annotations.AnalogSensorType
      * com.qualcomm.robotcore.hardware.configuration.annotations.DeviceProperties
      * com.qualcomm.robotcore.hardware.configuration.annotations.DigitalIoDeviceType
      * com.qualcomm.robotcore.hardware.configuration.annotations.I2cDeviceType
      * com.qualcomm.robotcore.hardware.configuration.annotations.MotorType
      * com.qualcomm.robotcore.hardware.configuration.annotations.ServoType
    * External libraries that use the following annotations can add new functionality to the Robot Controller:
      * org.firstinspires.ftc.ftccommon.external.OnCreate
      * org.firstinspires.ftc.ftccommon.external.OnCreateEventLoop
      * org.firstinspires.ftc.ftccommon.external.OnCreateMenu
      * org.firstinspires.ftc.ftccommon.external.OnDestroy
      * org.firstinspires.ftc.ftccommon.external.WebHandlerRegistrar
* Adds support for REV Robotics Driver Hub
* Adds fully custom userspace USB gamepad driver to Driver Station (see "Advanced Gamepad Features" menu in DS settings)
    * Allows gamepads to work on devices without native Linux kernel support (e.g. some Romanian Motorola devices)
    * Allows the DS to read the unique serial number of each gamepad, enabling auto-recovery of dropped gamepads even if two gamepads of the same model drop. *(NOTE: unfortunately this does not apply to Etpark gamepads, because they do not have a unique serial)*
    * Reading the unique serial number also provides the ability to configure the DS to assign gamepads to a certain position by default (so no need to do start+a/b at all)
    * The LED ring on the Xbox360 gamepad and the RGB LED bar on the PS4 gamepad is used to indicate the driver position the gamepad is bound to
    * The rumble motors on the Xbox360, PS4, and Etpark gamepads can be controlled from OpModes
    * The 2-point touchpad on the PS4 gamepad can be read from OpModes
    * The "back" and "guide" buttons on the gamepad can now be safely bound to robot controls (Previously, on many devices, Android would intercept these buttons as home button presses and close the app)
    * Advanced Gamepad features are enabled by default, but may be disabled through the settings menu in order to revert to gamepad support provided natively by Android
* Improves accuracy of ping measurement
    * Fixes issue where the ping time showed as being higher than reality when initially connecting to or restarting the robot
    * To see the full improvement, you must update both the Robot Controller and Driver Station apps
* Updates samples located at [/FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples](FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples)
    * Added ConceptGamepadRumble and ConceptGamepadTouchpad samples to illustrtate the use of these new gampad capabilities.
    * Condensed existing Vuforia samples into just 2 samples (ConceptVuforiaFieldNavigation & ConceptVuforiaFieldNavigationWebcam) showing how to determine the robot's location on the field using Vuforia. These both use the current season's Target images.  
    * Added ConceptVuforiaDriveToTargetWebcam to illustrate an easy way to drive directly to any visible Vuforia target.
* Makes many improvements to the warning system and individual warnings
    * Warnings are now much more spaced out, so that they are easier to read
    * New warnings were added for conditions that should be resolved before competing
    * The mismatched apps warning now uses the major and minor app versions, not the version code
    * The warnings are automatically re-enabled when a Robot Controller app from a new FTC season is installed
* Adds support for I2C transactions on the Expansion Hub / Control Hub without specifying a register address
    * See section 3 of the [TI I2C spec](https://www.ti.com/lit/an/slva704/slva704.pdf)
    * Calling these new methods when using Modern Robotics hardware will result in an UnsupportedOperationException
* Changes VuforiaLocalizer `close()` method to be public
* Adds support for TensorFlow v2 object detection models.
* Reduces ambiguity of the Self Inspect language and graphics.
* OnBotJava now warns about potentially unintended file overwrites
* Improves behavior of the Wi-Fi band and channel selector on the Manage webpage.

### Bug fixes
 * Fixes Robot Controller app crash on Android 9+ when a Driver Station connects
 * Fixes issue where an Op Mode was responsible for calling shutdown on the
   TensorFlow TFObjectDetector. Now this is done automatically.
 * Fixes Vuforia initialization blocks to allow user to chose AxesOrder. Updated
   relevant blocks sample opmodes.
 * Fixes [FtcRobotController issue #114](https://github.com/FIRST-Tech-Challenge/FtcRobotController/issues/114)
   LED blocks and Java class do not work
 * Fixes match logging for Op Modes that contain special characters in their names
 * Fixes Driver Station OpMode controls becoming unresponsive if the Driver Station was set to the landscape layout and an OnBotJava build was triggered while an OpMode was running
 * Fixes the Driver Station app closing itself when it is switched away from, or the screen is turned off
 * Fixes "black swirl of doom" (Infinite "configuring Wi-Fi Direct" message) on older devices
 * Updates the wiki comment on the OnBotJava intro page

