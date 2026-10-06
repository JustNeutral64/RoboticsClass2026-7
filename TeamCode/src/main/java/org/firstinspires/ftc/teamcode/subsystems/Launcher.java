package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Hardware;

/**
 * Flywheel launcher. Two counter rotating motors assumed, run by velocity
 * so the wheel speed holds steady between shots.
 *
 * If your launcher is a single motor, delete the "right" motor and the
 * references to it.
 */
public class Launcher {

    // Target wheel speed in encoder ticks per second. ADJUST once you know the
    // motor and gearing. A bare goBILDA 6000 rpm motor is roughly 2800 ticks/s
    // at full speed, so start lower and raise it until the shot distance is right.
    public static final double LAUNCH_VELOCITY = 1500; // ADJUST

    // How close to target counts as ready to fire. ADJUST.
    public static final double VELOCITY_TOLERANCE = 100;

    private final DcMotorEx left, right;

    public Launcher(HardwareMap hardwareMap) {
        left  = hardwareMap.get(DcMotorEx.class, Hardware.LAUNCHER_LEFT);
        right = hardwareMap.get(DcMotorEx.class, Hardware.LAUNCHER_RIGHT);

        // Counter rotating. ADJUST if both wheels should spin the same way.
        left.setDirection(DcMotor.Direction.REVERSE);
        right.setDirection(DcMotor.Direction.FORWARD);

        for (DcMotorEx m : new DcMotorEx[]{left, right}) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void spinUp() {
        left.setVelocity(LAUNCH_VELOCITY);
        right.setVelocity(LAUNCH_VELOCITY);
    }

    public void stop() {
        left.setVelocity(0);
        right.setVelocity(0);
    }

    /** True when both wheels are within tolerance of the target speed. */
    public boolean atSpeed() {
        return Math.abs(left.getVelocity() - LAUNCH_VELOCITY) < VELOCITY_TOLERANCE
                && Math.abs(right.getVelocity() - LAUNCH_VELOCITY) < VELOCITY_TOLERANCE;
    }

    public double getVelocity() {
        return left.getVelocity();
    }
}
