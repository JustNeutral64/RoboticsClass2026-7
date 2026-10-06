package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Hardware;

/**
 * Spinner used to clear or unjam balls.
 *
 * Assumed to be a single motor. If yours is a continuous rotation servo,
 * swap DcMotorEx for CRServo, drop the mode and zero power lines, and the
 * setPower calls stay the same.
 */
public class Spinner {

    public static final double SPIN_POWER = 1.0; // ADJUST

    private final DcMotorEx motor;

    public Spinner(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, Hardware.SPINNER);
        motor.setDirection(DcMotor.Direction.FORWARD); // ADJUST
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /** Spin in the clearing direction. */
    public void clear()   { motor.setPower(SPIN_POWER); }

    /** Spin the other way to unjam. */
    public void reverse() { motor.setPower(-SPIN_POWER); }

    public void stop()    { motor.setPower(0); }
}
