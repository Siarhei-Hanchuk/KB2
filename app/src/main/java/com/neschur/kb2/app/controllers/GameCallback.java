package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.Fighting;

public interface GameCallback {
    public void activateEntity(Entity entity);

    public void activateBattle(Fighting fighting);

    public void weekFinish();
}
