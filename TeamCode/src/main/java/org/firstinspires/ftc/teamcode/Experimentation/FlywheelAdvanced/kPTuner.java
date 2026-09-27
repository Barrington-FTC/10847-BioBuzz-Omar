package org.firstinspires.ftc.teamcode.Experimentation.FlywheelAdvanced;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Config
@TeleOp(name = "kPTuner")
public class kPTuner extends OpMode {

    Flywheel flywheel = new Flywheel();


    public double kS = 0.065;
    public double kV = 0.000116096;

    public static double kP = 0.05;
    public static double targetRPM = 1500;

    @Override
    public void init() {
        flywheel.init(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {

        double feedForward = (kV * targetRPM) + kS;

        double error = targetRPM - flywheel.getRPM();
        double feedBack = kP * error;

        double power = feedForward + feedBack;

        flywheel.setFlywheelPower(power);
        telemetry.addData("kP", "%.6f", kP);
        telemetry.addData("Error", error);
        telemetry.addData("RPM", "%.1f", flywheel.getRPM());
        telemetry.addData("Ticks Per Second", flywheel.getTicksPerSec());

        telemetry.update();
    }
}
