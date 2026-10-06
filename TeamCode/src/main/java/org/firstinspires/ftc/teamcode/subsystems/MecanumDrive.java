package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.util.Hardware;

/**
 * Field centric mecanum drivetrain.
 *
 * drive(forward, strafe, turn, fieldCentric) is the one method you call every loop.
 *   forward  positive drives the robot away from the driver
 *   strafe   positive drives the robot to the driver right
 *   turn     positive rotates the robot clockwise
 *
 * All four values are expected in the range -1 to 1.
 */
public class MecanumDrive {

    // ADJUST after a strafe test. 1.1 is a common starting value to correct
    // the mecanum tendency to under strafe. Set to 1.0 to disable.
    public static final double STRAFE_CORRECTION = 1.1;

    private final DcMotorEx frontLeft, frontRight, backLeft, backRight;
    private final IMU imu;

    public MecanumDrive(HardwareMap hardwareMap) {
        frontLeft  = hardwareMap.get(DcMotorEx.class, Hardware.FRONT_LEFT);
        frontRight = hardwareMap.get(DcMotorEx.class, Hardware.FRONT_RIGHT);
        backLeft   = hardwareMap.get(DcMotorEx.class, Hardware.BACK_LEFT);
        backRight  = hardwareMap.get(DcMotorEx.class, Hardware.BACK_RIGHT);

        // ADJUST directions after a drive test. Typical layout reverses the
        // left side. If the robot spins or drives backward, flip the offenders.
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        for (DcMotorEx m : new DcMotorEx[]{frontLeft, frontRight, backLeft, backRight}) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        imu = hardwareMap.get(IMU.class, Hardware.IMU);
        // ADJUST the two directions to match how the Control Hub is physically
        // mounted on the robot. Use the ConceptExploringIMUOrientations sample
        // if you are unsure which values are correct.
        RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(orientation));
    }

    /** Call when the driver wants to re align field centric forward. */
    public void resetYaw() {
        imu.resetYaw();
    }

    /** Current robot heading in radians. */
    public double getYaw() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    public void drive(double forward, double strafe, double turn, boolean fieldCentric) {
        double x = strafe;
        double y = forward;

        if (fieldCentric) {
            double heading = getYaw();
            double cos = Math.cos(-heading);
            double sin = Math.sin(-heading);
            double rotX = x * cos - y * sin;
            double rotY = x * sin + y * cos;
            x = rotX;
            y = rotY;
        }

        x *= STRAFE_CORRECTION;

        double denom = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1.0);
        frontLeft.setPower((y + x + turn) / denom);
        backLeft.setPower((y - x + turn) / denom);
        frontRight.setPower((y - x - turn) / denom);
        backRight.setPower((y + x - turn) / denom);
    }

    public void stop() {
        drive(0, 0, 0, false);
    }
}
