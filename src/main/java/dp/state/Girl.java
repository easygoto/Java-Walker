package dp.state;

/**
 * @author trink
 */
public class Girl {

    private String name;

    private BaseGirlState girlState = new GirlHappyState();

    public String getName() {
        return name;
    }

    public BaseGirlState getGirlState() {
        return girlState;
    }

    public void setGirlState(BaseGirlState girlState) {
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
