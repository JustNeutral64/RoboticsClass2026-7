package org.firstinspires.ftc.teamcode.pedropathing;

import com.pedropathing.tuning.autotune.Procedure;
import com.pedropathing.tuning.autotune.Tuner;

import org.firstinspires.ftc.teamcode.pedropathing.procedures.MecanumTuner;

public class Tuning {

    @Tuner
    public static Procedure mecanumTuner() {
        return new MecanumTuner();

        //enables pedros automatic drivetrain setup. upload
        // code to robot, then open
        //http://192.168.43.1:10158
    }

}