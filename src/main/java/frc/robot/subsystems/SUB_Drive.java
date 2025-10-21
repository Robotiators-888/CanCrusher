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
public class SUB_Drive extends SubsystemBase {

  private final DifferentialDrive drive;
  static SUB_Drive INSTANCE = null;
  WPI_TalonSRX LeftLeader;
  WPI_TalonSRX RightLeader;
  WPI_TalonSRX LeftFollower;
  WPI_TalonSRX RightFollower;


  static public SUB_Drive getInstance () {
    if (INSTANCE == null) {
      INSTANCE = new SUB_Drive();
      return INSTANCE;
    }
    else {
      return INSTANCE;
    }
  }

  private SUB_Drive() {
    // create brushed motors for drive
    LeftLeader = new WPI_TalonSRX(DriveConstants.LEFT_LEADER_ID);
    RightLeader = new WPI_TalonSRX(DriveConstants.RIGHT_LEADER_ID);

    LeftFollower = new WPI_TalonSRX(DriveConstants.LEFT_FOLLOWER_ID);
    RightFollower = new WPI_TalonSRX(DriveConstants.RIGHT_FOLLOWER_ID);
     
    // set up differential drive class
    drive = new DifferentialDrive(LeftLeader, RightLeader);
    LeftFollower.follow(LeftLeader);
    RightFollower.follow(RightLeader);
    LeftFollower.setInverted(true); 
    RightFollower.setInverted(true); 
    RightLeader.setInverted(true);
    //usb cable, weird type,  connect to rio, then phenix (note)
  }

  @Override
  public void periodic() {}

  // sets the speed of the drive motors
  public void driveArcade(double xSpeed, double zRotation) {
    drive.arcadeDrive(xSpeed, zRotation);
  }
}
