package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.models.Game;

public class BattleFinishMessage extends Message {
    private final boolean result;
    private final int authority;
    private final int money;

    BattleFinishMessage(Game game, I18n i18n, boolean result, int authority, int money) {
        super(null, game, i18n);
        this.result = result;
        this.authority = authority;
        this.money = money;
    }

    @Override
    public void action() {

    }

    @Override
    public String getText() {
        if (result) {
            return i18n.translate(R.string.battle_finish_win);
        } else {
            return i18n.translate(R.string.battle_finish_fail);
        }
    }
}
