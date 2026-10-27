package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Hardware;

/**
 * Intake roller. Single motor assumed.
 * If your intake is a continuous rotation servo instead, swap DcMotorEx
 * for CRServo and setPower stays the same.
 */
public class Intake {

    public static final double INTAKE_POWER = 1.0; // ADJUST

    private final DcMotorEx motor;

    public Intake(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, Hardware.INTAKE);
        motor.setDirection(DcMotor.Direction.FORWARD); // ADJUST
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

        /** Runs the intake inward to collect game elements. */
    public void in() {
        motor.setPower(INTAKE_POWER);
    }
    
    /** Reverses the intake to release or eject game elements. */
    public void out() {
        motor.setPower(-INTAKE_POWER);
    }
    
    /** Stops the intake motor. */
    public void stop() {
        motor.setPower(0);
    }
    
    /**
     * Sets the intake motor power directly.
     * Positive values run inward; negative values run outward.
     *
     * @param power motor power, normally between -1.0 and 1.0
     */
    public void setPower(double power) {
        motor.setPower(power);
    }
}
