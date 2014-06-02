package com.neschur.kb2.app.objs;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.ObjID;

public class Nave extends Obj{

	public Nave(Country country, int x, int y) {
		super(country, x, y);
	}

	@Override
	public int getID() {
		return ObjID.o_nave;
	}

	@Override
	public int action() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setXY(int x,int y){
		this.x=x;
		this.y=y;
	}

}
