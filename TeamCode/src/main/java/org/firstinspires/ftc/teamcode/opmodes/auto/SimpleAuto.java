package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Simple Auto")
public class SimpleAuto extends LinearOpMode {
    private DcMotor frontLeft,frontRight;
    private DcMotor backLeft, backRight;
    private DcMotor shooter,intake;

    @Override
    public void runOpMode(){
        // Match these names to the Robot Configuration
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Example mechanism names
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        intake = hardwareMap.get(DcMotor.class, "intake");

        // Match the directions from TeleOp
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        DcMotor[] driveMotors = {
                frontLeft,frontRight,backLeft,backRight
        };

        for (DcMotor motor: driveMotors){
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();
        private void drive(double forward, double strafe, double turn, long milliseconds)

        if (isStopRequested()) return;

        try{
            drive(0.25,0,0,700);
        }

    }

}
