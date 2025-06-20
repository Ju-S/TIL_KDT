public class Mouse {

    private String brand;

    private boolean rightKeyPressed;
    private boolean leftKeyPressed;
    private boolean wheelKeyPressed;

    private int wheelOffset;

    private int[] mouseMovedCord;

    public Mouse(String brand) {
        this.brand = brand;

        rightKeyPressed = false;
        leftKeyPressed = false;
        wheelKeyPressed = false;

        wheelOffset = 0;
        mouseMovedCord = new int[]{0, 0};
    }

    public String getBrand() {
        return brand;
    }

    public int getWheelOffset() {
        return wheelOffset;
    }

    public void wheelMoved(int wheelOffset) {
        this.wheelOffset += wheelOffset;
    }

    public boolean isRightKeyPressed() {
        return rightKeyPressed;
    }

    public void setRightKeyPressed(boolean isPressed) {
        rightKeyPressed = isPressed;
    }

    public boolean isLeftKeyPressed() {
        return leftKeyPressed;
    }

    public void setLeftKeyPressed(boolean isPressed) {
        leftKeyPressed = isPressed;
    }

    public boolean isWheelKeyPressed() {
        return wheelKeyPressed;
    }

    public void setWheelKeyPressed(boolean isPressed) {
        wheelKeyPressed = isPressed;
    }

    public void mouseMoved(int[] mouseMovedCord) {
        for(int i = 0; i < mouseMovedCord.length; i++) {
            this.mouseMovedCord[i] += mouseMovedCord[i];
        }
    }
}
