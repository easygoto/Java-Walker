package dp.Factory.Factory;

public class PlaneFactory implements VehicleFactory {

    @Override
    public Movable create() {
        return new Plane();
    }
}
