package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.models.Game;

import java.util.Random;

public class GoldChestMessage extends Message {
    private final int mode;

    GoldChestMessage(Entity entity, Game game) {
        super(entity, game);
        mode = (new Random()).nextInt(3);
    }

    @Override
    public String getText() {
        switch (mode) {
            case 0:
                return I18n.translate("entity_goldChest_messages_salary");
            case 1:
                return I18n.translate("entity_goldChest_messages_magickUp");
            case 2:
                // TODO
                return "-";
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
                player.upMagicMaxCount();
                break;
            case 2:
                // TODO
                break;
        }
    }
}
