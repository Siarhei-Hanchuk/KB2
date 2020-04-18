package by.siarhei.kb2.app;

public interface Storage {
    void saveGame(String data, String key);

    String loadGame(String key);
}
