package com.neschur.kb2.app.ui;

public interface Menu {
    public int getCount();
    public String getItemDescr(int i);
    public boolean select(int i);
    public void setAddition(Object data);
}
