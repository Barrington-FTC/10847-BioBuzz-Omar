package org.firstinspires.ftc.teamcode.Experimentation;

import com.pedropathing.utils.Angle;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class Turret {
    private DcMotorEx turret;
    private static final double ticksPerRev = 28;
    private static final double gearRatio = 1;

    private static final double ticksPerDegree = (ticksPerRev / 360.0) * gearRatio;

    public static final double MIN_TURRET_ANGLE_DEG = -180.0;
    public static final double MAX_TURRET_ANGLE_DEG = 180.0;

    public void init(HardwareMap hwMap) {
        turret = hwMap.get(DcMotorEx.class, "turret");
        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        turret.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        turret.setTargetPosition(0);
        turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        turret.setPower(1.0);
    }

    public void rotate(double xDistance, double yDistance, double heading) {
        double targetAngle = calculateAngle(xDistance, yDistance, heading);

        int targetTicks = (int) Math.round(targetAngle * ticksPerDegree);
        turret.setTargetPosition(targetTicks);
    }

    private double calculateAngle(double xDistance, double yDistance, double heading) {
        double targetAngle = Math.atan2(yDistance, xDistance);
        targetAngle = Math.toDegrees(targetAngle);

        // Get's turret's target angle relative to the robot
        targetAngle -= heading;
        targetAngle = AngleUnit.normalizeDegrees(targetAngle);

        return Range.clip(targetAngle, MIN_TURRET_ANGLE_DEG, MAX_TURRET_ANGLE_DEG);
    }

}
