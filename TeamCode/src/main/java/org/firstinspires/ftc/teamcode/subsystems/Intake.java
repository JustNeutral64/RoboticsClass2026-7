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

    public void in()   { motor.setPower(INTAKE_POWER); }
    public void out()  { motor.setPower(-INTAKE_POWER); }
    public void stop() { motor.setPower(0); }

    public void setPower(double power) { motor.setPower(power); }
}
