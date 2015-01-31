package com.neschur.kb2.app.warriors;

public interface Warrior {
    public String getTextId();
    public int getId();
    public int getPriceInShop();
    public int getDefence();
    public int getDamage();
    public int getStep();
    public boolean isFly();
    public boolean isShoot();
    public int getCountInShop();
}
