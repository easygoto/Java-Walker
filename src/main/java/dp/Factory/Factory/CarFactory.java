package dp.Factory.Factory;

public class CarFactory implements VehicleFactory {

    public Movable create() {
        return new Car();
    }
}