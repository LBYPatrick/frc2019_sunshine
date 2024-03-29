package frc.robot.hardware;

import frc.robot.software.*;


public class Chassis {

    static Motor l1,l2,l3,r1,r2,r3;
    static Solenoid shifter;

    static double speedFactor = 1;
    static boolean isFliped = false;

    static public void init() {
        l1 = new Motor(Statics.CHASSIS_L1);
        l2 = new Motor(Statics.CHASSIS_L2);
        l3 = new Motor(Statics.CHASSIS_L3);
        r1 = new Motor(Statics.CHASSIS_R1,true);
        r2 = new Motor(Statics.CHASSIS_R2,true);
        r3 = new Motor(Statics.CHASSIS_R3,true);

        shifter = new Solenoid(Statics.SHIFTER_PCM,
                               Statics.SHIFTER_F,
                               Statics.SHIFTER_R);
    }

    static public void setSpeedFactor(double factor) {
        speedFactor = factor; 
    }

    static public void shift() {
        shifter.actuate();
    }

    static public void drive(double y, double x) {

        final double left  = Utils.clipValue(y + x, -1.0, 1.0) * speedFactor * (isFliped? -1 : 1);
        final double right = Utils.clipValue(y - x, -1.0, 1.0) * speedFactor * (isFliped? -1 : 1);

        l1.setSpeed(left);
        l2.setSpeed(left);
        l3.setSpeed(left);
        r1.setSpeed(right);
        r2.setSpeed(right);
        r3.setSpeed(right);
        
    }

    static public void stop() {
        drive(0,0);
    }

    static public void flip() {
        isFliped = !isFliped;
    }
}

