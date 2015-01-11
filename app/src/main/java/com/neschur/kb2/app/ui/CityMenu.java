package com.neschur.kb2.app.ui;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.countries.World;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.entities.City;

public class CityMenu implements Menu {
    private City city;
    private Player player;
    private GameController gameController;

    public CityMenu(City city, GameController gameController){
        this.city = city;
        this.gameController = gameController;
        this.player = gameController.getPlayer();
    }

    @Override
    public String getItemDescription(int i) {
        switch (i) {
            case 0:
                return "Заключить контракт";
            case 1:
                if(gameController.getNave())
                    return "Продать корабль ($0)";
                return "Купить корабль ($500)";
            case 2:
                return "#Спросить о замке";
            case 3:
                return "#Переход к замку";
            case 4:
                if(player.isWallkick())
                    return "-";
                return "Купить стенобитное орудие ($3000)";
            case 5:
                return "Работники";
            case 6:
                return "Товар";
            default:
                return null;
        }
    }

    @Override
    public boolean select(int i) {
        switch (i) {
            case 1:
                if (gameController.getNave()){
                    gameController.destroyNave();
                }else{
                    player.changeMoney(-500);
                    gameController.createNave(4, 5);
                }
                return false;
            case 4:
                if(!player.isWallkick()){
                    player.changeMoney(-3000);
                    player.setWallkick();
                }
                return false;
//            case 6:
//                Menu m=new MenuCityWorkers();
//                m.setAddition(city);
//                ScreenController.pushMenus(m);
//                return false;
            default:
                return false;
        }

    }

    @Override
    public int getCount() {
        return 7;
    }

//    @Override
//    public void setAddition(Object data) {
//        city=(City)data;
//    }

}
