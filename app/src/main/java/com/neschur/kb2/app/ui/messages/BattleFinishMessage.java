package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.models.Game;

public class BattleFinishMessage extends Message {
    private final boolean result;

    BattleFinishMessage(Game game, boolean result, I18n i18n) {
        super(null, game, i18n);
        this.result = result;
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
