package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.entities.GoldChest;
import by.siarhei.kb2.app.server.models.Game;

import java.util.Random;

public class GoldChestMessage extends Message {
    private final int mode;

    GoldChestMessage(Entity entity, Game game, I18n i18n) {
        super(entity, game, i18n);
        mode = (new Random()).nextInt(3);
    }

    @Override
    public String getText() {
        switch (mode) {
            case 0:
                return i18n.translate("entity_goldChest_messages_salary");
            case 1:
                return i18n.translate("entity_goldChest_messages_magicUp");
            case 2:
                return i18n.translate("entity_goldChest_messages_randomMagic");
        }
        return null;
    }

    @Override
    public void action() {
        switch (mode) {
            case 0:
                player.changeSalary(((GoldChest) entity).getSalary());
                break;
            case 1:
                player.getMagic().upMagicMaxCount();
                break;
            case 2:
                player.getMagic().upRandomMagic();
                break;
        }
        // TODO:
//        entity.destroy();
    }
}
