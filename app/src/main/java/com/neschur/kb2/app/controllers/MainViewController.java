package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.GameGrid;

public interface MainViewController extends ViewController {
    public GameGrid getGameGrid();

    public void touchMenu(int item);
    public void touchUp();
    public void touchUpLeft();
    public void touchUpRight();
    public void touchDown();
    public void touchDownLeft();
    public void touchDownRight();
    public void touchRight();
    public void touchLeft();
    public void activateEntity(Entity entity);
}
