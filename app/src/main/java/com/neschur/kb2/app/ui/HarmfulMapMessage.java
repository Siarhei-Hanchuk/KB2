package com.neschur.kb2.app.ui;

import android.content.res.Resources;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Nave;

import java.util.Random;

/**
 * Created by siarhei on 17.1.15.
 */
public class HarmfulMapMessage extends Message{
    private int mode;
    private final int COUNT = 5;

    public HarmfulMapMessage(Resources resources, GameController gameController) {
        super(resources, gameController);
        mode = Math.abs((new Random()).nextInt()) % COUNT;
    }

    @Override
    public String getText() {
        switch (mode) {
            case 0:
                return resources.getString(R.string.entity_harmfulMap_message1);
            case 1:
                return resources.getString(R.string.entity_harmfulMap_message2);
            case 2:
                return resources.getString(R.string.entity_harmfulMap_message3);
            case 3:
                return resources.getString(R.string.entity_harmfulMap_message4);
            case 4:
                return resources.getString(R.string.entity_harmfulMap_message5);
        }
        return null;
    }

    public void action() {
        switch (mode) {
            case 0:
                for(int i=0; i<4; i++)
                    player.changeWorker(i, -player.getWorker(i));
                return;
            case 1:
                Nave nave = player.getNave();
                if(nave != null)
                    nave.destroy();
                return;
            case 2:
                // TODO
                return;
            case 3:
                // TODO
                return;
            case 4:
                player.changeMoney(-player.getMoney());
                return;
        }
    }
}
