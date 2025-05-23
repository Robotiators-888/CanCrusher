// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class DriveConstants {
    public static final int LEFT_LEADER_ID = 1; //TODO: Change IDs
    public static final int LEFT_FOLLOWER_ID = 2;
    public static final int RIGHT_LEADER_ID = 3;
    public static final int RIGHT_FOLLOWER_ID = 4;

    public static final int DRIVE_MOTOR_CURRENT_LIMIT = 60;
  }

  public static class OperatorConstants {
    public static final int kDriver1ControllerPort = 0;
    public static final int kDriver2ControllerPort = 1;
    public static final double kDriveDeadband = 0.05;
}

  public static class GroundIntakeConstants {
    public static final int kGroundIntakeCanID = 51;
    public static final double kGroundIntakeSpeed = -0.45;
    public static final double kGroundEjectSpeed = 0.45;
  }
  public static class GroundPivotConstants {
    public static final int kGroundPivotCanID = 52;
    public static final double kIntakePos = 135;
    public static final double kIntakeThreshold = 10; 
    public static final double kStowPos = 0;
    public static final double kScorePos = 25; 
  }
}
