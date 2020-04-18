package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.MapPoint;

import java.util.Random;

public class SorcererMessage extends Message {
    private int mode;

    SorcererMessage(MapPoint mapPoint, Game game, I18n i18n) {
        super(mapPoint, game, i18n);
    }

    @Override
    public String getText() {
        mode = (new Random()).nextInt(12) + 1;
        return i18n.translate("entity_sorcerer_message" + mode);
    }

    @Override
    public void action() {
        mapPoint.setEntity(null);
        switch (mode) {
            case 1:
                // TODO
            case 2:
                // TODO player.getMagic().magicPower
            case 3:
                player.changeAuthority(-player.getAuthority() / 2);
                break;
            case 4:
                for (int i = 0; i < 4; i++)
                    player.changeWorker(i, -player.getWorker(i));
                break;
            case 5:
                player.changeMoney(-player.getMoney());
                break;
            case 6:
                player.clearArmy();
                break;
            case 7:
                // TODO player.getMagic().
            case 8:
                // TODO player.getMagic().
            case 9:
                player.move(game.getWorld().getCountry((new Random()).nextInt(5)).getRandomLand());
                break;
            case 10:
                player.move(player.getCountry().getRandomLand());
                break;
            case 11:
                game.destroyNave();
                break;
            case 12:
                player.getMemory().clear();
                break;
        }
    }
}
