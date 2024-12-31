// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final PWMTalonSRX m_leftMotor1 = new PWMTalonSRX(0);
  private final PWMTalonSRX m_rightMotor1 = new PWMTalonSRX(2);
  private final PWMTalonSRX m_leftmotor2 = new PWMTalonSRX(1);
  private final PWMTalonSRX m_rightMotor2 = new PWMTalonSRX(3);

  private final DifferentialDrive m_robotDrive =new DifferentialDrive(m_leftMotor1::set, m_rightMotor1::set);
  private final Joystick m_stick = new Joystick(1);

  @Override
  public void robotInit() {
   
    m_rightMotor2.addFollower(m_rightMotor1);
    m_leftmotor2.addFollower(m_leftMotor1);
   

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotor1.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Get joystick values and scale them for speed control
    double forwardSpeed = -m_stick.getY() * 0.5; // Scale the forward speed to 50% of full speed
    double turnSpeed = -m_stick.getX() * 0.5;    // Scale the turn speed to 50% of full speed

    // Use arcade drive to control the robot
    m_robotDrive.arcadeDrive(forwardSpeed, turnSpeed);
  }
}
