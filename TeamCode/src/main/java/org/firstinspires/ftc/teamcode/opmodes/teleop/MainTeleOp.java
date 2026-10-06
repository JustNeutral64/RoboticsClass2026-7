package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "MainTeleOp")
public class MainTeleOp extends LinearOpMode{

  private DcMotor frontLeft;
  private DcMotor backLeft;
  private DcMotor frontRight;
  private DcMotor backRight;


  @Override
  public void runOpMode() {
    frontLeft = hardwareMap.get(DcMotor.class,"frontLeft");
    frontRight = hardwareMap.get(DcMotor.class,"frontRight");
    backLeft = hardwareMap.get(DcMotor.class,"backLeft");
    backRight = hardwareMap.get(DcMotor.class,"backRight");

    //because motors mounted on opposite sides physically oppose each other.
    frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
    backRight.setDirection(DcMotorSimple.Direction.REVERSE);

    //motors resist movement when power is 0
    for (DcMotor m : new DcMotor[]{frontLeft,frontRight,backLeft,backRight}) {
      m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    waitForStart();

    while (opModeIsActive()){

      double fwd = -gamepad1.left_stick_y;//stick y is inverted
      double strafe = gamepad1.left_stick_x;
      double turn = gamepad1.right_stick_x;

      double lfp = fwd+strafe+turn;
      double rfp = fwd-strafe-turn;
      double lbp = fwd-strafe+turn;
      double rbp = fwd+strafe-turn;

      double leftPower = fwd + turn;
      double rightPower = fwd - turn;

      //slow mode by holding left bumper
      double speedMultiplier = 1.0;
      if (gamepad1.left_bumper){
        speedMultiplier = 0.4;
      }
      leftPower *= speedMultiplier;
      rightPower *= speedMultiplier;

      //prevent calculated motor powers from going above 1 or below -1
      double max = Math.max((1.0),Math.max(
              Math.max(Math.abs(lfp),Math.abs(rfp)),
              Math.max(Math.abs(lbp), Math.abs(rbp))));

      frontLeft.setPower(lfp/max);
      frontRight.setPower(lfp/max);
      backLeft.setPower(lfp/max);
      backRight.setPower(lfp/max);
      //send calculated power to drivetrain motors
      frontLeft.setPower(leftPower);
      backLeft.setPower(leftPower);

      frontRight.setPower(rightPower);
      backRight.setPower(rightPower);

      //telemetry
      telemetry.addData("Drive", fwd);
      telemetry.addData("Turn", turn);
      telemetry.addData("Left Power", leftPower);
      telemetry.addData("Right Power", rightPower);
      telemetry.addData("Slow Mode", gamepad1.left_bumper);
      telemetry.addData("Status", "Running");
      telemetry.update();
    }
  }
}
