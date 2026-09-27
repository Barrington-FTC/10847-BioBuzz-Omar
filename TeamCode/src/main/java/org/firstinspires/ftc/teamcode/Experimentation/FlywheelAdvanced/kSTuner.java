package org.firstinspires.ftc.teamcode.Experimentation.FlywheelAdvanced;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Config
@TeleOp(name = "kSTuner")
public class kSTuner extends OpMode {

    Flywheel flywheel = new Flywheel();

    public static double kS = 0.065;

    @Override
    public void init() {
        flywheel.init(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {


        flywheel.setFlywheelPower(kS);
        telemetry.addData("kS", "%.6f", kS);
        telemetry.addData("RPM", flywheel.getRPM());
        telemetry.addData("Ticks Per Second", flywheel.getTicksPerSec());

        telemetry.update();
    }
}
