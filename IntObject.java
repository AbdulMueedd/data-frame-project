public class IntObject implements DataObject { 
    private int value;

    public IntObject(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Integer) { // value can only be an integer for this code to work as intended
            this.value = (Integer) value;
        } else { 
            throw new IllegalArgumentException("Invalid value type for IntObject.");
        }
    }

    @Override
    public int compareTo(DataObject other) {
        if (!(other instanceof IntObject)) { // if not an intElement, throw illegal argument exception, as this comparison is not legal
            throw new IllegalArgumentException("Invalid comparison between IntObject and " + other.getClass().getSimpleName());
        }
        return Integer.compare(this.value, ((IntObject) other).value);
    }
    @Override
public String toString() {
  return "IntObject: " + value;
}

}
