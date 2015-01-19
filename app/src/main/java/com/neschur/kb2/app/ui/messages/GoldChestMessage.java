package com.neschur.kb2.app.ui.messages;

import android.content.res.Resources;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

import java.util.Random;

public class GoldChestMessage extends Message {
    private int mode;

    public GoldChestMessage(Entity entity, Resources resources, GameController gameController) {
        super(entity, resources, gameController);
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
                player.changeSalary((entity.getCountry().getId() + 1) * 500);
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
