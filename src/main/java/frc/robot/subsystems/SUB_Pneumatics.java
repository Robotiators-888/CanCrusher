package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SUB_Pneumatics extends SubsystemBase{
    private static SUB_Pneumatics INSTANCE = null;

// Solenoid constructor is (6,7) the 6 is the solenoid input, 7 is solenoid output
    // TODO: change the input and output values for the new penumatits system
    static DoubleSolenoid piston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);
    //private final Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.REVPH, 0); 
    //singular solonoids for penumatic switches  /\
    Compressor Compressor = new Compressor(PneumaticsModuleType.CTREPCM);
     
    
    public SUB_Pneumatics(){
        piston.set(Value.kReverse);
    }

    // Causes Piston to fire forward
    public static void pistonGo() {
        piston.set(Value.kForward);
    }

    // Causes Piston to return to unfired position
    public static void PistonReverse() {
        piston.set(Value.kReverse);
    }

    // toggles the piston to fire again
    public void PistonToggle() {
        piston.toggle();
    }

    public static SUB_Pneumatics getInstance() {
        if (INSTANCE == null) {
          INSTANCE = new SUB_Pneumatics();
        }
        return INSTANCE;
    }

    public void periodic() {
    }

    //  m_solenoid.set(m_stick.getRawButton(kSolenoidButton));
    // - can be used for enabling the piston using a true or false statment
    // Or using an instan command with the button pressed being on true


    // --COMMPRESSOR COMMANDS--
     // --Get compressor current draw.
 // return m_compressor.getCurrent();

     // --Get whether the compressor is active.
//  return m_compressor.isEnabled();

     // --Get the digital pressure switch connected to the PCM/PH.
     // --The switch is open when the pressure is over ~120 PSI.
//  return m_compressor.getPressureSwitchValue();

   // --Disable closed-loop mode on the compressor.
//m_compressor.disable();
  // --Enable closed-loop mode based on the digital pressure switch connected to the PCM/PH.
   // --The switch is open when the pressure is over ~120 PSI.
//m_compressor.enableDigital();


}