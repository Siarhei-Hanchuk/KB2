package com.neschur.kb2.app.models;

/**
 * Created by siarhei on 6.6.14.
 */

public class Player {
    //private World world=World.getInstance();
    private static Player player;
    private boolean wallkick;
    private int workers[]=new int[3];
    private int money;
    private int authority;
    private boolean innave;
    private int country;
    private int useMagican=0;
    private int magic=0;
    private int avalCountry=10;

    private int X;
    private int Y;

//    public static Player getInstance(){
//        if(player==null)
//            player=new Player();
//        return player;
//    }

    public Player(){
        country=0;
        setInnave(false);
        X=5;
        Y=5;
        wallkick=false;
        money=20000;
        authority=50;
        //for(gint i=0;i<10;i++)
        //army[i].armid=0;
        //workers.carpenter=4;
        //workers.woodsman=6;
        //workers.groundsman=0;
        //workers.stonesman=3;
    }

//    public void action(int key){
//        switch (key) {
//            case 87:
//                ScreenController.pushMenus(new MenuPlayerWorker());
//                break;
//            case 78:
//                ScreenController.pushMenus(new MenuPlayerChCountry());
//                break;
//            default:
//                break;
//        }
//    }

    public void move(int x, int y){
        this.X = x;
        this.Y = y;
    }

//    public void move(int dx, int dy){
//        if(X+dx<2 || X+dx>62 || Y+dy<2 || Y+dy>62)
//            return;
//        MapPoint mp=world.getCountry(country).getMap(X+dx, Y+dy);
//        if(mp.obj==null){
//            if(mp.land==ObjID.l_land || mp.land==ObjID.l_plot || mp.land==ObjID.l_sand || (mp.land==ObjID.l_water && innave) ){
//                X+=dx;
//                Y+=dy;
//                if(mp.land==ObjID.l_land)
//                    innave=false;
//                else{
//                    World.getInstance().setNave(X, Y);
//                }
//                ScreenController.update(true);
//                return;
//            }
//        }
//        if(mp.obj==null)
//            return;
//        if(mp.getObj().getID()==ObjID.o_nave){
//            innave=true;
//            X+=dx;
//            Y+=dy;
//            ScreenController.update(true);
//            return;
//        }
//        mp.getObj().action();
//		/*switch (mp.obj.getID()) {
//		case ObjID.o_city:
//			Screen.setMenu(new MenuCity());
//			break;
//		case ObjID.o_goldchest:
//			Menu menu=new MenuGold();
//			menu.setAddition(new Integer(500));
//			Screen.setMenu(menu);
//			break;
//		case ObjID.o_nave:
//			innave=true;
//			X+=dx;
//			Y+=dy;
//			Screen.update();
//			break;
//		default:
//			break;
//		}*/
//    }

    public void setCountry(int coun){
        country=coun;
        X=2;
        Y=2;
    }

    public int getCountry(){
        return country;
    }

    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }

    public boolean isInnave() {
        return innave;
    }

    public void setInnave(boolean innave) {
        this.innave = innave;
    }

//    public void changeMoney(int d){
//        money+=d;
//        notifyStatusController();
//    }

    public void changeAutho(int d){
        authority+=d;
    }

    public Integer getMoney() {
        return money;
    }
    public Integer getAuthority() {
        return authority;
    }

	/*public int getAvalCountry() {
		return avalCountry;
	}*/

    public void upAvalCountry() {
        this.setAvalCountry(this.getAvalCountry() + 1);
    }

    public boolean isWallkick() {
        return wallkick;
    }

//    public void setWallkick() {
//        this.wallkick = true;
//        notifyStatusController();
//    }

    public int getUseMagican() {
        return useMagican;
    }

    public void upUseMagican() {
        this.useMagican++;
    }

    public int getMagic() {
        return magic;
    }

//    public void upMagic() {
//        this.magic++;
//        notifyStatusController();
//    }

//    void notifyStatusController(){
//        StatusController.update();
//    }

    public int getAvalCountry() {
        return avalCountry;
    }

    public void setAvalCountry(int avalCountry) {
        this.avalCountry = avalCountry;
    }

    public void upWorker(int id, int count){
        workers[id-1]+=count;
    }

    public int getWorker(int id){
        return workers[id-1];
    }
}

