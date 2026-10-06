# PedroPathing (not wired yet)

This folder is empty of code so the project builds clean today. PedroPathing
is added only after the drivetrain and localization are working, because a path
follower is useless until the robot knows where it is.

## What has to happen first

1. Mecanum TeleOp drives correctly (motor directions and field centric confirmed).
2. Localization hardware chosen and installed. This is the open decision.
   The usual options, easiest first:
   - goBILDA Pinpoint  (IMU plus two dead wheels, computes pose on board)
   - SparkFun OTOS     (optical tracking, single sensor)
   - three dead wheels through an OctoQuad
   - drive motor encoders only (least accurate, use only as a fallback)
   Note for this season: in BIOBUZZ the AprilTags sit on moving game elements,
   so they cannot be used for absolute field position. Odometry is the real
   localization source.

## Install order once localization is picked

1. Add the PedroPathing Maven repo and dependency to the build files
   (repo line in build.dependencies.gradle, implementation line in
   TeamCode/build.gradle). Pull the exact current version from
   pedropathing.com rather than copying an old snippet, since it changes.
2. Generate the Constants and localizer config for the chosen sensor.
3. Run the tuning OpModes in this order, following the pedropathing.com guide:
   localization test, then forward and lateral velocity, then the
   translational, heading and drive PIDs.
4. Only then write the autonomous OpModes in opmodes/auto, building paths with
   the PedroPathing visualizer and calling the subsystems for scoring actions.

Tell me which localizer you land on and I will generate the Constants file and
a first tuning plus a first auto path.
