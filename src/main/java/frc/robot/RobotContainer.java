// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoCommand;
import frc.robot.subsystems.SUB_Drive;
import frc.robot.subsystems.SUB_Pneumatics;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private static SUB_Drive driveSubsystem = SUB_Drive.getInstance();
  private static SUB_Pneumatics pneumatics = SUB_Pneumatics.getInstance();



  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController Driver1 =
      new CommandXboxController(OperatorConstants.kDriver1ControllerPort);

  // private final CommandXboxController Driver2 = new
  // CommandXboxController(OperatorConstants.kDriver2ControllerPort);

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
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in
   * {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings() {
    // Set the default command for the drive subsystem to an instance of the
    // DriveCommand with the values provided by the joystick axes on the driver
    // controller. The Y axis of the controller is inverted so that pushing the
    // stick away from you (a negative value) drives the robot forwards (a positive
    // value). Similarly for the X axis where we need to flip the value so the
    // joystick matches the WPILib convention of counter-clockwise positive
    // driveSubsystem.setDefaultCommand(new DriveCommand(
    //     () -> -Driver1.getLeftY() * (Driver1.getHID().getRightBumperButton() ? 1 : 0.5),
    //     () -> -Driver1.getRightX(), driveSubsystem));

    // Mathutil.applyDeadband makes sure no small movements are registered like stick drift, get raw axis returns a value of an axis between 1 and 0
    // I think axis zero is the left joysticks y axis and axis 3 is te right joysticks y axis
    // kDriveDeadband tells applyDeadband the value of movements that shouldn't be registered
    driveSubsystem.setDefaultCommand(new RunCommand(() -> driveSubsystem.drive(
      MathUtil.applyDeadband(Driver1.getRawAxis(5)*0.3,OperatorConstants.kDriveDeadband),
      MathUtil.applyDeadband(-Driver1.getRawAxis(1)*0.3,OperatorConstants.kDriveDeadband)),driveSubsystem));


    Driver1.x()
        .onTrue(new SequentialCommandGroup(
            new ParallelCommandGroup(new InstantCommand(() -> pneumatics.pistonGo(), pneumatics)),
            new WaitCommand(1.5), new ParallelCommandGroup(
                new InstantCommand(() -> pneumatics.pistonReverse(), pneumatics))));

    Driver1.y().onTrue(new InstantCommand(() -> pneumatics.pistonToggle(), pneumatics));

    // TODO: add bahner sensor(detect if there is a blockage), E-stop(may not be nessary),
    // speaker(if there is time and avaiable speaker)
    // Possibly remove: Groundintake + Groundpivot (CANbot should not need it)
    // Functions: LED(default state and cancrushing sequence. possibly change colors if there is a
    // blockage),
    // Sensor(after time passes(2-5 sec) and object is still there(or in a speficied distance),
    // activate blockage mode)
    // Blockage mode(robot disables itself(turn of or disable penuamtics and possibly driving)
    // and lEDs and sound to alert that there is a blockage in the robot)
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
