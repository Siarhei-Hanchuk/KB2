package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.models.Game;

import java.util.Random;

public class SpellMessage extends Message {
    private int mode = 0;

    SpellMessage(Entity entity, Game game, I18n i18n) {
        super(entity, game, i18n);
    }

    @Override
    public String getText() {
        mode = (new Random()).nextInt(15);
        return i18n.translate("entity_spell_message" + mode);
    }

    @Override
    public void action() {
        switch (mode) {
            case 0:
                return;
            case 1:
                for (int i = 0; i < 4; i++)
                    player.changeWorker(i, -player.getWorker(i));
                return;
            case 2:
                if (game.naveExists())
                    game.destroyNave();
                return;
            case 3:
                player.getMemory().clear();
                return;
            case 4:
                player.clearArmy();
                return;
            case 5:
                player.changeMoney(-player.getMoney());
                return;
            case 6:
                player.setBigEars((new Random()).nextInt(5));
                return;
            case 7:
                // TODO
                return;
            case 8:
                player.move(game.getPlayer().getCountry().getRandomLand());
                return;
            case 9:
                player.changeAuthority(+player.getAuthority());
                return;
            case 10:
                player.changeMoney(+player.getMoney());
                return;
            case 11:
                // TODO
                return;
            case 12:
                // TODO
                return;
            case 13:
                player.setImportantDocs((new Random()).nextInt() % 5);
                return;
            case 14:
                // TODO
                return;
        }
//        TODO:
//        entity.destroy();
    }
}
