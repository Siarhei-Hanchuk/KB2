package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.models.Game;

import java.util.Random;

public class SorcererMessage extends Message {
    private int mode;

    SorcererMessage(Entity entity, Game game, I18n i18n) {
        super(entity, game, i18n);
    }

    @Override
    public String getText() {
        mode = (new Random()).nextInt(12) + 1;
        return i18n.translate("entity_sorcerer_message" + mode);
    }

    @Override
    public void action() {
        switch (mode) {
            case 0:
                // TODO
            case 1:
                // TODO player.getMagic().magicPower
            case 2:
                player.changeAuthority(-player.getAuthority() / 2);
                break;
            case 3:
                for (int i = 0; i < 4; i++)
                    player.changeWorker(i, -player.getWorker(i));
                break;
            case 4:
                player.changeMoney(-player.getMoney());
                break;
            case 5:
                player.clearArmy();
                break;
            case 6:
                // TODO player.getMagic().
            case 7:
                // TODO player.getMagic().
            case 8:
                player.move(game.getWorld().getCountry((new Random()).nextInt(5)).getRandomLand());
                break;
            case 9:
                player.move(player.getCountry().getRandomLand());
                break;
            case 10:
                game.destroyNave();
                break;
            case 11:
                player.getMemory().clear();
                break;
        }
    }
}
