package org.firstinspires.ftc.teamcode.Experimentation;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp
public class FlywheelPIDFTuning extends OpMode {
    public DcMotorEx Flywheel;

    public double highVelocity = 1500;
    public double lowVelocity = 900;

    double targetVelocity = highVelocity;

    double P = 0;
    double F = 0;
    double[] stepSizes = {10.0, 1.0, 0.1 , 0.01, 0.001, 0.0001};
    int stepIndex = 0;
    @Override
    public void init() {
        Flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        Flywheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
        Flywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        telemetry.addLine("Init Complete!");

    }

    @Override
    public void loop() {
        //Gamepad Controls:
        if (gamepad1.yWasPressed()) {
            if (targetVelocity == highVelocity) {
                targetVelocity = lowVelocity;
            }
            else {
                targetVelocity = highVelocity;
            }
        }

        if (gamepad1.bWasPressed()) {
            stepIndex = (stepIndex + 1) % stepSizes.length;
        }

        if (gamepad1.dpadLeftWasPressed()) {
            F -= stepSizes[stepIndex];
        }

        if (gamepad1.dpadRightWasPressed()) {
            F += stepSizes[stepIndex];
        }

        if (gamepad1.dpadDownWasPressed()) {
            P -= stepSizes[stepIndex];
        }

        if (gamepad1.dpadUpWasPressed()) {
            P += stepSizes[stepIndex];
        }
        // Set PIDF coefficents:

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
        Flywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        // Set Velocity:

        Flywheel.setVelocity(targetVelocity);

        double currentVelocity = Flywheel.getVelocity();

        double error = targetVelocity - currentVelocity;

        telemetry.addData("Target Velocity", targetVelocity);
        telemetry.addData("Current Velocty", currentVelocity);
        telemetry.addData("Error", "%.2f", error);

        telemetry.addLine("------------------------------------");

        telemetry.addData("Tuning P", "%.4f (D-pad U/D)", P);
        telemetry.addData("Tuning F", "%.4f (D-pad L/R)", F);
        telemetry.addData("Step Size", "%.4f (B Button)", stepSizes[stepIndex]);

    }
}
