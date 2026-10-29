package org.firstinspires.ftc.teamcode.drive;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;



import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


import java.lang.Math;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp


public class AutonomousModeLeft_Copy extends LinearOpMode {
    private Blinker control_Hub;
    private Blinker expansion_Hub_2;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private Servo servoRight;
    private Servo servoLeft;
    //private DcMotor leftShoulder;
    //private DcMotor rightShoulder;
    //private DcMotor elbow;
   
    private ElapsedTime runtime = new ElapsedTime();
   
    public void driveInDirection(double direction, double power, double right_stick) {
        double x = power * Math.sin(direction);
        double y = power * Math.cos(direction);
       
        double fl = y + x - right_stick;
        double fr = y - x + right_stick;
        double bl = y - x - right_stick;
        double br = y + x + right_stick;


        double max = Math.max(1.0,
            Math.max(Math.abs(fl),
            Math.max(Math.abs(fr),
            Math.max(Math.abs(bl), Math.abs(br)))));


        frontLeft.setPower(fl / max);
        frontRight.setPower(fr / max);
        backLeft.setPower(bl / max);
        backRight.setPower(br / max);
    }
    
    
        public void runOpMode() {

        control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
        expansion_Hub_2 = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");

        //flywheel = hardwareMap.get(DcMotor.class, "flywheel");
        funnel = hardwareMap.get(DcMotor.class, "funnel");
        //cycler1 = hardwareMap.get(DcMotor.class, "cycler1");
        //cycler2 = hardwareMap.get(DcMotor.class, "cycler2");

        servoLeft = hardwareMap.get(Servo.class, "servoLeft");
        servoRight = hardwareMap.get(Servo.class, "servoRight");

        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //flywheel.setDirection(DcMotorSimple.Direction.REVERSE);
        //cycler1.setDirection(DcMotorSimple.Direction.REVERSE);
        //cycler2.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
       
        double timer;
       
        double forward = 0;
        double right = Math.PI/2; 
        double left = Math.PI/-2; 
        double backward = Math.PI;
       
        double delayedRun = runtime.seconds();
       
        while (opModeIsActive()) {


            if (gamepad1.a) {
                servoLeft.setPosition(0.0);
                servoRight.setPosition(.2);
            }
           
            double timeInSeconds = runtime.seconds() - delayedRun;
           
            if(timeInSeconds <= 0.5) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 1);
            }

            else if(timeInSeconds <= 1) {
                driveInDirection(right, 0, 0);
                telemetry.addData("Phase", 2);
            }
            else if(timeInSeconds <= 1.5) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 3);
            }
            else if(timeInSeconds <= 2) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 4);
            }
            else if(timeInSeconds <= 2.5) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 5);
            }
            else if(timeInSeconds <= 3) {
                driveInDirection(left, 0, 0);
                telemetry.addData("Phase", 6);
            }
            else if(timeInSeconds <= 3.5) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 7);
            }
            else if(timeInSeconds <= 4) {
                driveInDirection(left, 0, 0);
                telemetry.addData("Phase", 8);
        /*
            }
            else if(timeInSeconds <= 4.8) {
                driveInDirection(0, 0, 0);
                telemetry.addData("Phase", 9);
                flywheel.setPower(1);
            }
            else if (timeInSeconds <= 7) {
                cycler1.setPower(1);
                cycler2.setPower(1);
                telemetry.addData("Phase", 10);
            }
            else if (timeInSeconds <= 11) {
                flywheel.setPower(0);
                cycler1.setPower(0);
                cycler2.setPower(0);
                driveInDirection(0, 0, 1);
                telemetry.addData("Phase", 11);
            }
        */
            else if(timeInSeconds <= 11.5) {
                driveInDirection(right, 0, 0);
                telemetry.addData("Phase",12);
            }
            else if(timeInSeconds <= 12) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 13);
            }
            else if(timeInSeconds <= 12.5) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 14);
            }
            else if(timeInSeconds <= 13) {
                driveInDirection(left, 0, 0);
                telemetry.addData("Phase", 15);
            }
            else if(timeInSeconds <= 13.5) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 16);
            }
            else if(timeInSeconds <= 14) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 17);
            }
            else if(timeInSeconds <= 14.5) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 18);
            }
            else if(timeInSeconds <= 15) {
                driveInDirection(left, 0, 0);
                telemetry.addData("Phase", 19);
            }
            else if(timeInSeconds <= 15.5) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 20);
            }
            else if(timeInSeconds <= 16) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 21);
            }
            else if(timeInSeconds <= 16.5) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 22);
            }
            else if(timeInSeconds <= 17) {
                driveInDirection(forward, 1, 0);
                telemetry.addData("Phase", 23);
            }
            telemetry.addData("Time", runtime.seconds());
           
            telemetry.update();
        }
   
    }
   
   
   
   
   
   
   
   
   
   
   
}