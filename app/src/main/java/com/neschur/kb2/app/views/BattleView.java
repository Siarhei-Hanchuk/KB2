package com.neschur.kb2.app.views;

import android.content.Context;

import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.GameController;

/**
 * Created by siarhei on 20.1.15.
 */
public class BattleView extends View {
    private BattleController battleController;

    public BattleView(Context context, GameController gameController,
                      BattleController battleController, ViewClosable closeCallback) {
        super(context, gameController, closeCallback);
        this.battleController = battleController;
    }
}
