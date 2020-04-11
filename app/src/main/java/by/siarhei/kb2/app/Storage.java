package by.siarhei.kb2.app;

public interface Storage {
    boolean saveGame(String data, String key);

    String loadGame(String key);
}
