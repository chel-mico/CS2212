package cryptoTrader.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
/**
 * Test 3-2 Trade Failure
 * @author Endreas Yohannes
 *
 */
public class PerformTradeFail {
	/**
	 * Main method for the test
	 * @param args
	 */
	public static void main (String args[]) {
		LoginTestSuccess.main(null);
		try {
			Thread.sleep(500);
			Robot bot = new Robot();
			
			//first broker
			//putting in the broker name and coins
			bot.mouseMove(1300, 80);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK); //left click
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); //left click
			Thread.sleep(50);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK); //left click
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); //left click
			Thread.sleep(50);
			
			int[] brokerProfile = {
				KeyEvent.VK_A, 
				KeyEvent.VK_TAB,
				KeyEvent.VK_B,
				KeyEvent.VK_I,
				KeyEvent.VK_T,
				KeyEvent.VK_C,
				KeyEvent.VK_O,
				KeyEvent.VK_I,
				KeyEvent.VK_N
			}; //a TAB bitcoin
			for (int i : brokerProfile) {
				bot.keyPress(i);
				Thread.sleep(50);
				bot.keyRelease(i);
			}
			//selecting strategy B
			bot.mouseMove(1900, 80);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK); //left click
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); //left click
			Thread.sleep(50);
			bot.mouseMove(1900, 140);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK); //left click
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); //left click
			Thread.sleep(50);
			//clicking add row
			bot.mouseMove(1600, 670);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK); //left click
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); //left click
			
			//second broker (should fail)
			//putting in the broker name and coins
			bot.mouseMove(1300, 100);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK); //left click
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); //left click
			Thread.sleep(50);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK); //left click
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); //left click
			
			int[] brokerProfile2 = {
				KeyEvent.VK_B, 
				KeyEvent.VK_TAB,
				KeyEvent.VK_B,
				KeyEvent.VK_I,
				KeyEvent.VK_T,
				KeyEvent.VK_C,
				KeyEvent.VK_O,
				KeyEvent.VK_I,
				KeyEvent.VK_N
			};
			for (int i : brokerProfile) {
				bot.keyPress(i);
				Thread.sleep(50);
				bot.keyRelease(i);
			}
			//selecting strategy B
			bot.mouseMove(1900, 100);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK); //left click
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); //left click
			Thread.sleep(50);
			bot.mouseMove(1900, 160);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK); //left click
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); //left click
			Thread.sleep(50);
			//clicking perform trade
			bot.mouseMove(1050, 700); //perform trade coordinates
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK); //left click
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); //left click
		} catch (AWTException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}