package by.siarhei.kb2.app;

public interface I18n {
    String translate(String key);

    String translate(int key);

    String translate(String key, String... replaces);

    String translate(String key, int... replaces);
}
