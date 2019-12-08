package com.trink.State;

public class Girl {

    private String name;

    private GirlState girlState = new GirlHappyState();

    public String getName() {
        return name;
    }

    public GirlState getGirlState() {
        return girlState;
    }

    public void setGirlState(GirlState girlState) {
        this.girlState = girlState;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void smile() {
        girlState.smile();
    }

    public void cry() {
        girlState.cry();
    }

    public void say() {
        girlState.say();
    }
}
