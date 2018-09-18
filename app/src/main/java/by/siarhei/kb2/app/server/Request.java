package by.siarhei.kb2.app.server;

public class Request {
    public static int ACTION_OK = 1;
    public static int ACTION_SELECT = 2;
    public static int ACTION_EXIT = 3;
    public static int ACTION_MOVE = 4;

    private int x;
    private int y;
    private int action = 0;
    private int menuItem = 0;

    public Request() {

    }

    public void setMoveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public void setMenuItem(int menuItem) {
        this.menuItem = menuItem;
    }

    public int getMenuItem() {
        return menuItem;
    }
}
