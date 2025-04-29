package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SUB_Pneumatics extends SubsystemBase{
    private static SUB_Pneumatics INSTANCE = null;

    public SUB_Pneumatics() {
      
    }

    public static SUB_Pneumatics getInstance() {
        if (INSTANCE == null) {
          INSTANCE = new SUB_Pneumatics();
        }
        return INSTANCE;
    }

    public void periodic() {
    }
}