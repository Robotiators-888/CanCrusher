// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.GroundIntakeConstants;
import frc.robot.Constants.GroundPivotConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.SUB_GroundIntake;
import frc.robot.subsystems.SUB_GroundPivot;
import frc.robot.subsystems.SUB_Pneumatics;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private static CANDriveSubsystem driveSubsystem = new CANDriveSubsystem();
  public static SUB_GroundIntake groundIntake = SUB_GroundIntake.getInstance();
  public static SUB_GroundPivot groundPivot = SUB_GroundPivot.getInstance();
  public static SUB_Pneumatics pneumatics = SUB_Pneumatics.getInstance();


  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController Driver1 = new CommandXboxController(OperatorConstants.kDriver1ControllerPort);

  private final CommandXboxController Driver2 = new CommandXboxController(OperatorConstants.kDriver2ControllerPort);

  // The autonomous chooser
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Set up command bindings
    configureBindings();

    // Set the options to show up in the Dashboard for selecting auto modes. If you
    // add additional auto modes you can add additional lines here with
    // autoChooser.addOption
    autoChooser.setDefaultOption("Autonomous", new AutoCommand(driveSubsystem));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Set the default command for the drive subsystem to an instance of the
    // DriveCommand with the values provided by the joystick axes on the driver
    // controller. The Y axis of the controller is inverted so that pushing the
    // stick away from you (a negative value) drives the robot forwards (a positive
    // value). Similarly for the X axis where we need to flip the value so the
    // joystick matches the WPILib convention of counter-clockwise positive
    driveSubsystem.setDefaultCommand(new DriveCommand(
        () -> -Driver1.getLeftY() *
            (Driver1.getHID().getRightBumperButton() ? 1 : 0.5),
        () -> -Driver1.getRightX(),
        driveSubsystem));

    groundIntake.setDefaultCommand(
          new RunCommand(() -> groundIntake.groundIntakeDetection(()->groundPivot.nearIntakeSetpoint()), groundIntake));
  
    groundPivot.setDefaultCommand(
          new RunCommand(() -> groundPivot.drivePivotPID(), groundPivot));

  Driver2.start().onTrue(new InstantCommand(()-> groundPivot.changeSetpoint(GroundPivotConstants.kIntakePos)));
                Driver2.leftStick().onTrue(new InstantCommand(()-> groundPivot.changeSetpoint(GroundPivotConstants.kStowPos)));
                Driver2.back().onTrue(new InstantCommand(()-> groundPivot.changeSetpoint(GroundPivotConstants.kScorePos)));
                Driver2.rightStick().whileTrue(new RunCommand(()->groundIntake.setGroundIntake(GroundIntakeConstants.kGroundEjectSpeed)));

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return autoChooser.getSelected();
  }
}
