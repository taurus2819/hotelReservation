package model;

public class FreeRoom  extends Room{

    public FreeRoom(){
        super();
        this.price = 0.0;
        System.out.println("Free room called");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
