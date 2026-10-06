package org.firstinspires.ftc.teamcode.util;

/**
 * Central list of the hardware config names.
 * These strings must match exactly what you name each device in the
 * Driver Station robot configuration. Change them here once and every
 * subsystem picks up the change.
 */
public final class Hardware {

    private Hardware() {}

    // Drivetrain (mecanum, four motors)
    public static final String FRONT_LEFT  = "frontLeft";
    public static final String FRONT_RIGHT = "frontRight";
    public static final String BACK_LEFT   = "backLeft";
    public static final String BACK_RIGHT  = "backRight";

    // Control Hub built in IMU
    public static final String IMU = "imu";

    // Mechanisms
    public static final String INTAKE         = "intake";
    public static final String LAUNCHER_LEFT  = "launcherLeft";
    public static final String LAUNCHER_RIGHT = "launcherRight";
    public static final String LIFT           = "lift";
    public static final String SPINNER        = "spinner";
}
