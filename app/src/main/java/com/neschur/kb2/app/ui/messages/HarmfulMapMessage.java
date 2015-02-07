package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Game;

import java.util.Random;

public class HarmfulMapMessage extends MapMessage {
    HarmfulMapMessage(Entity entity, Game game, I18n i18n) {
        super(entity, game, i18n);
    }

    @Override
    protected int getCount() {
        return 5;
    }

    @Override
    public String getText() {
        return i18n.translate("entity_harmfulMap_message" + (mode + 1));
    }

    @Override
    public void action() {
        switch (mode) {
            case 0:
                for (int i = 0; i < 4; i++)
                    player.changeWorker(i, -player.getWorker(i));
                return;
            case 1:
                if (game.getNave())
                    game.destroyNave();
                return;
            case 2:
                player.getMemory().clear();
                return;
            case 3:
                player.clearArmy();
                return;
            case 4:
                player.changeMoney(-player.getMoney());
                return;
            case 5:
                player.setBigEars((new Random()).nextInt(5));
                return;
            case 6:
                player.move(game.getPlayer().getCountry().getRandomLand());
                return;
        }
        entity.destroy();
    }
}
