package org.firstinspires.ftc.teamcode.Experimentation;

public class CustomPIDF {
    private double kS , kV, kP;
    public CustomPIDF(double kS, double kV, double kP) {
        this.kS = kS;
        this.kV = kV;
        this.kP = kP;

    }
    public double customPower (double currentRPM, double targetRPM) {
        if (targetRPM == 0) {
            return 0.0;
        }

        double feedForward = (kV * targetRPM) + kS;

         double error = targetRPM - currentRPM;
         double feedBack = kP * error;

         double power = feedForward + feedBack;
         power = Math.max(0.0, Math.min(1.0, power));
         return power;
    }
}
