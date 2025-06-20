import java.awt.*;
import java.awt.event.InputEvent;

public class Exam02 {
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        int[] goalCord = new int[]{1857, 16};
        int delay = 5;

        //robot.mouseMove(0, Toolkit.getDefaultToolkit().getScreenSize().height);

        robot.delay(1000);

        for (int x = MouseInfo.getPointerInfo().getLocation().x,
             y = MouseInfo.getPointerInfo().getLocation().y;
             x != goalCord[0] || y != goalCord[1]; ) {

            if (x < goalCord[0]) {
                x++;
            } else if (x > goalCord[0]) {
                x--;
            }

            if (y < goalCord[1]) {
                y++;
            } else if (y > goalCord[1]) {
                y--;
            }

            robot.mouseMove(x, y);
            robot.delay(delay);
        }
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
