package com.neschur.kb2.app.ui;

import com.neschur.kb2.app.countries.World;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.entities.City;

public class CityMenu implements KMenu {
    private City city;
    private Player player;
    private World world;

    public CityMenu(City city, World world, Player player){
        this.city = city;
        this.world = world;
        this.player = player;
    }

    @Override
    public String getItemDescr(int i) {
        switch (i) {
            case 0:
                return "Заключить контракт";
            case 1:
                if(player.isInnave())
                    return "Продать корабль ($0)";
                return "Купить корабль ($500)";
            case 2:
                return "#Спросить о замке";
            case 3:
                return "#Переход к замку";
            case 4:
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
    public boolean proc(int i) {
        Player pl = player;
        switch (i) {
//            case 2:
//                if(world.getNave())
//                    world.clearNave();
//                else{
//                    pl.changeMoney(-500);
//                    world.newNave(4,5);
//                }
//                return false;
            case 5:
                if(!pl.isWallkick()){
                    pl.changeMoney(-3000);
                    pl.setWallkick();
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

    @Override
    public void setAddition(Object data) {
        city=(City)data;
    }

//    @Override
//    public MenusType getType() {
//        return MenusType.MENU;
//    }

}
