public class DoubleObject implements DataObject {
  private double value;

  public DoubleObject(double value) {
    this.value = value;
  }

  @Override
  public Double getValue() {
    return value;
  }

  @Override
  public void setValue(Object value) {
    if (value instanceof Double) {
      this.value = (Double) value;
    } else {
      throw new IllegalArgumentException("Invalid value type for DoubleObject.");
    }
  }

  @Override
  public int compareTo(DataObject other) {
    if (!(other instanceof DoubleObject)) {
      throw new IllegalArgumentException(
          "Invalid comparison between DoubleObject and " + other.getClass().getSimpleName());
    }
    return Double.compare(this.value, ((DoubleObject) other).value);
  }
  @Override
public String toString() {
  return "DoubleObject: " + value;
}

}
