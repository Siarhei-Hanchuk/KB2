package com.neschur.kb2.app.warriors;

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
