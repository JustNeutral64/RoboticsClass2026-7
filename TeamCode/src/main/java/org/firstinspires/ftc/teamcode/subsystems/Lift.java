package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Hardware;

/**
 * Lift on an encoder, driven to preset heights with RUN_TO_POSITION.
 *
 * IMPORTANT: the encoder is zeroed in the constructor, so the lift must be
 * resting at its lowest point when the OpMode initializes.
 *
 * Preset heights are in encoder ticks and must be measured on the real robot.
 */
public class Lift {

    public static final int GROUND = 0;    // ADJUST
    public static final int LOW    = 500;  // ADJUST
    public static final int HIGH   = 1500; // ADJUST

    public static final double LIFT_POWER = 0.8; // ADJUST

    private final DcMotorEx motor;

    public Lift(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, Hardware.LIFT);
        motor.setDirection(DcMotor.Direction.FORWARD); // ADJUST so up is positive
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void goTo(int ticks) {
        motor.setTargetPosition(ticks);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(LIFT_POWER);
    }

    public void ground() { goTo(GROUND); }
    public void low()    { goTo(LOW); }
    public void high()   { goTo(HIGH); }

    /**
     * Manual nudge, for example on a gamepad stick. Leaves RUN_TO_POSITION and
     * holds the current target when power is near zero.
     */
    public void setManual(double power) {
        if (Math.abs(power) < 0.05) {
            goTo(motor.getCurrentPosition());
            return;
        }
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(power);
    }

    public int getPosition() { return motor.getCurrentPosition(); }
    public boolean isBusy()  { return motor.isBusy(); }
}
