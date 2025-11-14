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
    public static final int LEFT_LEADER_ID = 21; //TODO: Change talon ids 
    public static final int LEFT_FOLLOWER_ID = 22;
    public static final int RIGHT_LEADER_ID = 23;
    public static final int RIGHT_FOLLOWER_ID = 24;

    public static final int DRIVE_MOTOR_CURRENT_LIMIT = 60;
  }

  public static class OperatorConstants {
    public static final int kDriver1ControllerPort = 0;
    public static final int kDriver2ControllerPort = 1;
    public static final double kDriveDeadband = 0.05;
}

public static class LED {
  public static final int kPWMPort = 9;
  public static final double kColorGreen = 0.77;
  public static final double kColorRed = 0.61;
  public static final double kParty_Palette_Twinkles = -0.53;
}
}
