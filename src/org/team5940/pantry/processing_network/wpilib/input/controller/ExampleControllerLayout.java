package org.team5940.pantry.processing_network.wpilib.input.controller;

/**
 * This is an example for what a controller interface would look like. This
 * helps record what the controller ports correspond to. This makes it so you
 * don't have to check the ports everytime the controls are changed and it makes
 * the code more readable. The values should correspond to the port for
 * getRawAxis() and getRawButton().
 * 
 * @author Michael Bentley
 *
 */
public interface ExampleControllerLayout {

	/**
	 * This would store all the corresponding values for getRawAxis() in the
	 * controller.
	 *
	 */
	public interface Axis {
		public static final int LEFT_TRIGGER = 0;

		public static final int RIGHT_TRIGGER = 1;

		public static final int RIGHT_JOYSTICK_Y = 2;

		public static final int RIGHT_JOYSTICK_X = 3;

		public static final int LEFT_JOYSTICK_Y = 4;

		public static final int LEFT_JOYSTICK_X = 5;
	}

	/**
	 * This would store all the corresponding values for getRawButton() in the
	 * controller. Buttons are 1 indexed for some reason. 
	 *
	 */
	public interface Buttons {
		public static final int X_BUTTON = 1;

		public static final int Y_BUTTON = 2;

		public static final int A_BUTTON = 3;

		public static final int B_BUTTON = 4;
	}
}
