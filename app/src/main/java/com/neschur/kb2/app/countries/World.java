package com.neschur.kb2.app.countries;

/**
 * Created by siarhei on 2.6.14.
 */
public class World {
    private static World world = null;
//    private Nave Onave;

//    public static World getInstance(){
//        if(world==null)
//            world=new World();
//        return world;
//    }

    private Country country[] = new Country[5];
//    private int naveX;
//    private int naveY;
//    //private int naveC;
//    //private boolean nave;

    public Country getCountry(int i) {
        return country[i];
    }

//    public Country getCurrentCountry() {
//        return country[Player.getInstance().getCountry()];
//    }

//    public int getNaveX() {
//        return naveX;
//    }
//
//    public int getNaveY() {
//        return naveY;
//    }

	/*public int getNaveC() {
        return naveC;
	}*/

    public World() {
        country[0] = new Country1();
        country[1] = new Country2();
        country[2] = new Country3();
        country[3] = new Country4();
        country[4] = new Country5();
    }

//    public boolean getNave(){
//        if(Onave==null)
//            return false;
//        return true;
//    }

//    public void clearNave() {
//        Onave.delete();
//        Onave=null;
//        //naveC=-1;
//    }

//    public void newNave(int x,int y){
//        Onave=new Nave(country[Player.getInstance().getCountry()], x, y);
//    }

//    public void setNave(int X, int Y) {
//        Onave.move(X,Y);
//        //nave=true;
//        //Onave.delete();
//		/*naveX=X;
//		naveY=Y;
//		naveC=0;
//		country[naveC].map[naveX][naveY].setObj(new Nave(country[naveC],naveX,naveY));*/
//    }

}

