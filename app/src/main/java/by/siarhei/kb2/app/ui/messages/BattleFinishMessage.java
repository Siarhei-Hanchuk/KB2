package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.models.Game;

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
            return i18n.translate("battle_finish_win",
                    Integer.toString(authority), Integer.toString(money));
        } else {
            return i18n.translate("battle_finish_fail", Integer.toString(authority));
        }
    }
}
