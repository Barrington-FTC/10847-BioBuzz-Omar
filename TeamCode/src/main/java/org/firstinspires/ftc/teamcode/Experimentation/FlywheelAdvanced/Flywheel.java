package org.firstinspires.ftc.teamcode.Experimentation.FlywheelAdvanced;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Flywheel {

    private DcMotorEx Flywheel;

    private final double ticksPerRev = 28;

    private double kS = 0.065, kV = 0.000116096, kP = 0.05;


    public void init(HardwareMap hwMap) {
        Flywheel = hwMap.get(DcMotorEx.class, "flywheel");
        Flywheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    public void setFlywheelPower(double power) {
        Flywheel.setPower(power);
    }

    public double getTicksPerSec() {
        return Flywheel.getVelocity();
    }

    public double getRPM() {
        return Math.abs(getTicksPerSec() / ticksPerRev * 60);
    }



}
