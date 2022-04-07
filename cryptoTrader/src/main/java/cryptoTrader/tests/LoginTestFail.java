package cryptoTrader.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import cryptoTrader.gui.Login;

public class LoginTestFail {
	public static void main (String args[]) {
		try {
			Robot bot = new Robot();
			Login.main(null);
			Thread.sleep(500);
			//full login: username: le, password: test3
			int[] login = {
				KeyEvent.VK_L, 
				KeyEvent.VK_E, 
				KeyEvent.VK_TAB, 
				KeyEvent.VK_T, 
				KeyEvent.VK_E, 
				KeyEvent.VK_S, 
				KeyEvent.VK_T,
				KeyEvent.VK_2
			}; //le TAB test2
			for (int i : login) {
				bot.keyPress(i);
				Thread.sleep(50);
				bot.keyRelease(i);
			}
			//move mouse to click login
			bot.mouseMove(200, 175); //move mouse to login button
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK); //left click
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); //left click
		} catch (AWTException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
