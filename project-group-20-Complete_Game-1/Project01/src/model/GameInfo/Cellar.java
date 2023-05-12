package model.GameInfo;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Cellar extends Building{
    private Double maxCapacity;

    protected Double space = maxCapacity;

    HashMap<String, Double> elements = new HashMap<>();

    public Cellar(Double maxCapacity, Government owner, String type, Integer maxHitpoint, Integer needWorkers, Integer price, Integer neededStone, Integer neededWood) {
        super(owner, type, maxHitpoint, needWorkers, price, neededStone, neededWood);
        this.maxCapacity = maxCapacity;
    }

 /* public double capacity(){
        return ((double)maxCapacity - entity(elements) );
    }

    public int entity(HashMap<String, Double> hash) {
        AtomicInteger i = new AtomicInteger();
        hash.forEach((key, value) -> {
            i.addAndGet((int) value);
        });
        return i.get();
    }*/

    public void setElements(String elementsKey,Double amount){
        elements.put(elementsKey,amount);
    }

    public double getElements(String good) {
        return elements.get(good);
    }

    public HashMap<String, Double> getElements() {return elements;}


    public void changeElements(String elementsKey,Integer change){
        elements.replace(elementsKey, (elements.get(elementsKey))+change );
    }

    public Double getCapacity() {
        return space;
    }

    public void setCapacity(Double capacity) {
        space = capacity;
    }

}
