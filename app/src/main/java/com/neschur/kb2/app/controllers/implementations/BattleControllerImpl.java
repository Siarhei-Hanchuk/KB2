package com.neschur.kb2.app.controllers.implementations;

import com.neschur.kb2.app.View;
import com.neschur.kb2.app.controllers.ApplicationController;
import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.battle.BattleField;
import com.neschur.kb2.app.models.battle.MapPointBattle;

public class BattleControllerImpl extends ApplicationController implements BattleController {
    private final BattleField battleField;
    private final View battleView;
    private final Fighting fighting;

    public BattleControllerImpl(ViewController controller, Fighting fighting) {
        this.battleField = new BattleField(controller.getGame().getPlayer(), fighting, this);
        this.fighting = fighting;
        this.battleView = getViewFactory().getBattleView(this);
        setContentView(battleView);
    }

    @Override
    public MapPointBattle[][] getMap() {
        return battleField.getMapPoints();
    }

    @Override
    public void select(int x, int y) {
        battleField.select(x, y);
        updateView();
    }

    @Override
    public int getSelectedX() {
        return battleField.getSelectedX();
    }

    @Override
    public int getSelectedY() {
        return battleField.getSelectedY();
    }

    @Override
    public void battleFinish(boolean win) {
        int authority = fighting.getAuthority() / 40; //??
        int money = fighting.getAuthority() * 40; //??
        if(win){
            getGame().getPlayer().changeAuthority(authority);
            getGame().getPlayer().changeMoney(money);
        } else {
            getGame().getPlayer().changeAuthority(-authority);
            MapPoint land = getGame().getPlayer().getCountry().getRandomLand();
            getGame().getPlayer().move(land.getX(), land.getY());

        }
        setContentView(getViewFactory().getViewBattleMessageView(this, win, authority, money));
    }

    @Override
    public void updateView() {
        updateView(0);
    }

    @Override
    public void updateView(int delay) {
        battleView.refresh(delay);
    }

    @Override
    public void viewClose() {
        new MainViewControllerImpl();
    }
}
