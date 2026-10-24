/*
Copyright 2024 FIRST Tech Challenge Team 21881

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; //REMOVE THE 23!!!
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp Drive", group= "Linear OpMode")
public class TeleOpDrive extends LinearOpMode {
//Drive motors
    DcMotor frontLeft, frontRight, backLeft, backRight, intakeMotor;// add flywheelMotor
// the class that does the computations for driving. left joy stick moves, right joy stick rotates
    public void driveInDirection(double x, double y, double rx){

        double fl = y + x + rx;
        double fr = y - x + rx;
        double bl = y - x - rx;
        double br = y + x - rx;

        // Normalize so no value exceeds 1
        double max = Math.max(Math.abs(fl), Math.max(Math.abs(fr),
                    Math.max(Math.abs(bl), Math.abs(br))));
        if (max > 1.0) {
            fl /= max;
            fr /= max;
            bl /= max;
            br /= max;
        }


        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        backLeft.setPower(bl);
        backRight.setPower(br);
        
        
    }

    @Override
    public void runOpMode() { // when initialized button is pressed
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");// connecting hardware with variables
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);// correcting motors that are spinning wrong direction
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);// if motors are zero, then stop moving
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();// when button start is pressed

        while (opModeIsActive()) {
            double yl1 = Math.abs(gamepad1.left_stick_x) > 0.05 ? gamepad1.left_stick_x : 0; // Forward/Backward
            double xl1 = Math.abs(gamepad1.left_stick_y) > 0.05 ? gamepad1.left_stick_y : 0;  // Strafe Left/Right
            double yr1 = Math.abs(gamepad1.right_stick_y) > 0.05 ? gamepad1.right_stick_y : 0;

            driveInDirection(-xl1, yl1, yr1);
            //--- INTAKE CONTROL LOGIC ---
            double intakePower = gamepad1.right_trigger;
            double outtakePower = gamepad1.left_trigger;


            if (gamepad1.right_trigger_pressed) {
                intakeMotor.setPower(1); //makes the motor suck items
            } else if (gamepad1.left_trigger_pressed) {
                intakeMotor.setPower(-1); //Makes the motor spit items
            } else {
                intakeMotor.setPower(0.0); //Stops the motor if neither trigger is held
            }
            //-----------------------------
            //Second Game Pad with flywheel on left_trigger
            //double flywheel_Power = gamepad2.left_trigger;
            //if (flywheel_Power > 0.1) {
            //    flywheelMotor.setPower(flywheel_Power); // make sthe fly wheel run
            //} else {
             //   flywheelMotor.setPower(0.0); //Stops the motor if neither trigger is held
            //}
           telemetry.addData("Joystick Y (Forward)", yl1);
            telemetry.addData("Joystick X (Strafe)", xl1);
            telemetry.addData("Joystick RX (Rotate)", yr1);
            telemetry.addData("Intake",intakePower );
            telemetry.addData("outtake", outtakePower );
            //telemetry.addData("flyWheel",flywheel_Power );
            telemetry.addData("Front Left Power", frontLeft.getPower());
            
            telemetry.update();
        }
    }
}

