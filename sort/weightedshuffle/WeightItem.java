package sort.weightedshuffle;

public class WeightItem<T> {
    public T item;
    public Double weight;

    public WeightItem(T item, Double weight) {
        this.item = item;
        this.weight = weight;
    }
}
