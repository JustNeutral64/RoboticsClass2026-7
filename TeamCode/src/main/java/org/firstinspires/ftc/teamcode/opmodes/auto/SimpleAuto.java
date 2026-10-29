
package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Simple Auto")
public class SimpleAuto extends LinearOpMode {

    private DcMotor frontLeft, frontRight;
    private DcMotor backLeft, backRight;

    @Override
    public void runOpMode() {

        // Initialize drivetrain
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Match these directions to your working TeleOp
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        DcMotor[] motors = {
                frontLeft, frontRight, backLeft, backRight
        };

        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor.setZeroPowerBehavior(
                    DcMotor.ZeroPowerBehavior.BRAKE
            );
        }

        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        try {
            // DRIVE FORWARD
            frontLeft.setPower(0.20);
            frontRight.setPower(0.20);
            backLeft.setPower(0.20);
            backRight.setPower(0.20);

            // WAIT ONE SECOND
            sleep(1000);
        }
        finally {
            // STOP ALL MOTORS
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
    }
}

