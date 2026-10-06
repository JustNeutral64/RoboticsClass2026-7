package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Holds every subsystem in one place. Both TeleOp and Autonomous build a
 * Robot, so the hardware setup lives in exactly one spot.
 *
 *   Robot robot = new Robot(hardwareMap);
 *   robot.drive.drive(...);
 *   robot.launcher.spinUp();
 */
public class Robot {

    public final MecanumDrive drive;
    public final Intake intake;
    public final Launcher launcher;
    public final Lift lift;
    public final Spinner spinner;

    public Robot(HardwareMap hardwareMap) {
        drive    = new MecanumDrive(hardwareMap);
        intake   = new Intake(hardwareMap);
        launcher = new Launcher(hardwareMap);
        lift     = new Lift(hardwareMap);
        spinner  = new Spinner(hardwareMap);
    }
}
