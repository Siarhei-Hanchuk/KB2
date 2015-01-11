package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class Nave extends Entity implements Action{
    Country country;

	public Nave(Country country, int x, int y) {
		super(country, x, y);
        this.country = country;
	}

    public void destroy() {
        country.getMap(this.x,this.y).entity = null;
    }

	@Override
	public int getID() {
		return R.drawable.nave;
	}

	public void action() {

	}

	public void move(int x,int y){
        country.getMap(x,y).entity = this;
        country.getMap(this.x,this.y).entity = null;
		this.x=x;
		this.y=y;
	}

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
