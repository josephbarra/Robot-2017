package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot{
	String autoSelected;
    SendableChooser<String> chooser;
    AutoFunctions functions;
    AutoModes auto;
    VisionTargeting vision;
    Constants constants = new Constants();
    DriverStation d = DriverStation.getInstance();
    //DriveTrain drive = new DriveTrain(constants);
    FlyWheel flywheel = new FlyWheel(constants);
    GroundCollector collector = new GroundCollector(constants);
    Joystick driverJoy = new Joystick(0);
    PIDController turnController;
    double rotateToAngleRate;
    static final double kP = 0.03;
    static final double kI = 0.00;
    static final double kD = 0.00;
    static final double kF = 0.00;
    static final double kToleranceDegrees = 2.0f;	
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser<String>();
        chooser.addDefault("Do Nothing", "DoNothing");
        chooser.addObject("Cross Baseline", "CrossBaseline");
        chooser.addObject("Middle Gear", "MiddleGear");
        chooser.addObject("Side Gear, Red or Blue", "SideGear");
        //        chooser.addObject("Red: Right Gear", "RedRight");
//        chooser.addObject("Blue: Right Gear", "BlueRight");
//        chooser.addObject("Red: Left Gear", "RedLeft");
//        chooser.addObject("Blue: Left Gear", "BlueLeft");
        chooser.addObject("Red: Middle Gear to Hopper", "RedMiddleHopper");
        chooser.addObject("Blue: Middle Gear to Hopper", "BlueMiddleHopper");
        chooser.addObject("Red: Right Gear to Hopper", "RedRightHopper");
        chooser.addObject("Blue: Right Gear to Hopper", "BlueRightHopper");
        chooser.addObject("Red: Left Gear to Hopper", "RedLeftHopper");
        chooser.addObject("Blue: Left Gear to Hopper", "BlueLeftHopper");
        chooser.addObject("Red: Middle Gear to Boiler", "RedMiddleBoiler");
        chooser.addObject("Blue: Middle Gear to Boiler", "BlueMiddleBoiler");
        chooser.addObject("Red: Right Gear to Boiler", "RedRightBoiler");
        chooser.addObject("Blue: Right Gear to Boiler", "BlueRightBoiler");
        chooser.addObject("Red: Left Gear to Boiler", "RedLeftBoiler");
        chooser.addObject("Blue: Left Gear to Boiler", "BlueLeftBoiler");
        chooser.addObject("Red: Hopper to Boiler", "RedHopperBoiler");
        chooser.addObject("Blue: Hopper to Boiler", "BlueHopperBoiler");
        chooser.addObject("Red: Start at Boiler", "RedStartBoiler");
        chooser.addObject("Blue: Start at Boiler", "BlueStartBoiler");
        SmartDashboard.putData("Auto choices", chooser);
        Double f = Double.parseDouble(SmartDashboard.getString("DB/String 4", ""));
        flywheel.setF(f);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
		autoSelected = SmartDashboard.getString("Auto Selector", "Do Nothing");
		vision = new VisionTargeting("");	//TODO insert IP Address here
		//functions = new AutoFunctions(drive, flywheel, collector, vision);
		auto = new AutoModes(functions);
		System.out.println("Auto selected: " + autoSelected);

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case "DoNothing":
    		auto.doNothing();
    		break;
    	case "CrossBaseline":
    		auto.crossBaseline();
    		break;
    	case "MiddleGear":
    		auto.middlePeg();
    		break;
    	case "SideGear":
    		auto.sidePeg();
    		break;
//    	case "RedRight":
//    		auto.rightPegRed();
//    		break;
//    	case "BlueRight":
//    		auto.rightPegBlue();
//    		break;
//    	case "RedLeft":
//    		auto.leftPegRed();
//    		break;
//    	case "BlueLeft":
//    		auto.leftPegBlue();
//    		break;
    	case "RedMiddleHopper":
    		auto.middleHopperRed();
    		break;
    	case "BlueMiddleHopper":
    		auto.middleHopperBlue();
    		break;
    	case "RedRightHopper":
    		auto.rightHopperRed();
    		break;
    	case "BlueRightHopper":
    		auto.rightHopperBlue();
    		break;
    	case "RedLeftHopper":
    		auto.leftHopperRed();
    		break;
    	case "BlueLeftHopper":
    		auto.leftHopperBlue();
    		break;
    	case "RedMiddleBoiler":
    		auto.middleBoilerRed();
    		break;
    	case "BlueMiddleBoiler":
    		auto.middleBoilerBlue();
    		break;
    	case "RedRightBoiler":
    		auto.rightBoilerRed();
    		break;
    	case "BlueRightBoiler":
    		auto.rightBoilerBlue();
    		break;
    	case "RedLeftBoiler":
    		auto.leftBoilerRed();
    		break;
    	case "BlueLeftBoiler":
    		auto.leftBoilerBlue();
    		break;
    	case "RedHopperBoiler":
    		auto.hopperBoilerRed();
    		break;
    	case "BlueHopperBoiler":
    		auto.hopperBoilerBlue();
    		break;
    	case "RedStartBoiler":
    		auto.startBoilerRed();
    		break;
    	case "BlueStartBoiler":
    		auto.startBoilerBlue();
    		break;
    	default:
    		turnController.setSetpoint(-90.0f);
    		break;

    	}
    	
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	  //drive.drive(driverJoy.getRawAxis(Constants.JOYSTICK_LEFT_AXIS_UPDOWN),
    			  //driverJoy.getRawAxis(Constants.JOYSTICK_RIGHT_TRIGGER), 
    			  //driverJoy.getRawAxis(Constants.JOYSTICK_LEFT_TRIGGER));
//     	double speed = driverJoy.getRawAxis(constants.DRIVER_JOYSTICK_PORT);
//     	double rightSpeed = driverJoy.getRawAxis(constants.JOYSTICK_RIGHT_TRIGGER);
//     	double leftSpeed = driverJoy.getRawAxis(constants.JOYSTICK_LEFT_TRIGGER);
//     	if(speed > 0 || rightSpeed > 0 || leftSpeed > 0){
//     		drive.drive(speed, rightSpeed, leftSpeed);
//     	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	double dashDataCollector = SmartDashboard.getNumber("DB/Slider 1", 0.0);
    	double RPMS, p, i, d;
    	RPMS = Double.parseDouble(SmartDashboard.getString("DB/String 0", ""));
    	p = Double.parseDouble(SmartDashboard.getString("DB/String 1", ""));
    	i = Double.parseDouble(SmartDashboard.getString("DB/String 2", ""));
    	d = Double.parseDouble(SmartDashboard.getString("DB/String 3", ""));
    	

    	if(driverJoy.getRawButton(1)){
    		collector.intake(dashDataCollector/5);
    	}
    	if(driverJoy.getRawButton(2)){
    		collector.stopCollector();
    	}
    	if(driverJoy.getRawButton(3)){
    		flywheel.shootWithEncoders(RPMS, p, i, d);
    	}
    	if(driverJoy.getRawButton(4)){
    		flywheel.stopFlywheel();
    	}
    }
    
}

