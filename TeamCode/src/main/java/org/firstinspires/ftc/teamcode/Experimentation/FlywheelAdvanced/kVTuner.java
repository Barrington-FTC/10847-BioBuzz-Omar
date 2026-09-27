package org.firstinspires.ftc.teamcode.Experimentation.FlywheelAdvanced;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Config
@TeleOp(name = "kVTuner")
public class kVTuner extends OpMode {

    Flywheel flywheel = new Flywheel();

    public static double kV;
    public double kS = 0.065;

    public double goalRPM = 1500;

    @Override
    public void init() {
        flywheel.init(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {

        double power = (kV * goalRPM) + kS;

        flywheel.setFlywheelPower(power);
        telemetry.addData("kV", "%.6f", kV);
        telemetry.addData("RPM", flywheel.getRPM());
        telemetry.addData("Ticks Per Second", flywheel.getTicksPerSec());

        telemetry.update();
    }
}
