package by.siarhei.kb2.app.server.warriors;

public interface Warrior {
    String getTextId();

    int getId();

    int getPriceInShop();

    int getDefence();

    int getDamage();

    int getStep();

    boolean isFly();

    boolean isShoot();

    int getCountInShop();
}
