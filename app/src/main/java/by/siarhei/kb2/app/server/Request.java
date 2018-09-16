package by.siarhei.kb2.app.server;

public class Request {
    private int x;
    private int y;

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
}
