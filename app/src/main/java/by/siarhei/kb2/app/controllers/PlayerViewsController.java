package by.siarhei.kb2.app.controllers;

import by.siarhei.kb2.app.server.models.Player;

public interface PlayerViewsController extends ViewController {
    Player getPlayer();
}
