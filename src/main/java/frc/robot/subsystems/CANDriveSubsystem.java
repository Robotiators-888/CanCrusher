// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Talon; 
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; 
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

// Class to drive the robot over CAN
public class CANDriveSubsystem extends SubsystemBase {

  private final Talon leftleader;
  private final Talon leftfollower;
  private final Talon rightleader;
  private final Talon rightfollower;

  private final DifferentialDrive drive;

  public CANDriveSubsystem() {
    // create brushed motors for drive
    WPI_TalonSRX LeftLeader = new WPI_TalonSRX(DriveConstants.LEFT_LEADER_ID);
    WPI_TalonSRX RightLeader = new WPI_TalonSRX(DriveConstants.RIGHT_LEADER_ID);

    WPI_TalonSRX leftFollower = new WPI_TalonSRX(DriveConstants.LEFT_FOLLOWER_ID);
    WPI_TalonSRX rightFollower = new WPI_TalonSRX(DriveConstants.RIGHT_FOLLOWER_ID);
     
    // set up differential drive class
    drive = new DifferentialDrive(LeftLeader, RightLeader);
    LeftFollower.follow(LeftLeader);
    RightFollower.follow(RightLeader);

  
//usb cable, weird type,  connect to rio, then phenix (note)

    // Remove following, then apply config to right leader
    //config.disableFollowerMode();
    //rightLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    // Set conifg to inverted and then apply to left leader. Set Left side inverted
    // so that postive values drive both sides forward
    //config.inverted(true);
    //leftLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {}

  // sets the speed of the drive motors
  public void driveArcade(double xSpeed, double zRotation) {
    drive.arcadeDrive(xSpeed, zRotation);
  }
}
