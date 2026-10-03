package org.firstinspires.ftc.teamcode.Experimentation.FlywheelAdvanced;

import com.bylazar.ftcontrol.panels.Panels;
import com.bylazar.ftcontrol.panels.integration.TelemetryManager; // Updated Telemetry import
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "kPTuner")
public class kPTuner extends OpMode {

    Flywheel flywheel = new Flywheel();

    // Changed type from PanelsTelemetry to TelemetryManager
    private TelemetryManager panelsTelemetry;

    public double kS = 0.065;
    public double kV = 0.000116096;

    public static double kP = 0.05;
    public static double targetRPM = 1500;

    @Override
    public void init() {
        flywheel.init(hardwareMap);

        // If Panels.getTelemetry() still shows red, change to Panels.INSTANCE.getTelemetry();
        panelsTelemetry = Panels.getTelemetry();
    }

    @Override
    public void loop() {

        double feedForward = (kV * targetRPM) + kS;

        double error = targetRPM - flywheel.getRPM();
        double feedBack = kP * error;

        double power = feedForward + feedBack;

        flywheel.setFlywheelPower(power);

        // Send telemetry to Panels UI (http://192.168.43.1:8001)
        panelsTelemetry.debug("kP", String.format("%.6f", kP));
        panelsTelemetry.debug("Error", String.format("%.2f", error));
        panelsTelemetry.debug("RPM", String.format("%.1f", flywheel.getRPM()));
        panelsTelemetry.debug("Ticks Per Second", flywheel.getTicksPerSec());

        // Update both Panels Dashboard and Driver Station
        panelsTelemetry.update(telemetry);
    }
}