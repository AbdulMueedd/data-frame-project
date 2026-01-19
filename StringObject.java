public class StringObject implements DataObject {
    private String value;

    public StringObject(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof String) {
            this.value = (String) value;
        } else {
            throw new IllegalArgumentException("Invalid value type for StringObject.");
        }
    }

    @Override
    public int compareTo(DataObject other) {
        if (!(other instanceof StringObject)) {
            throw new IllegalArgumentException("Invalid comparison between StringObject and " + other.getClass().getSimpleName());
        }
        return this.value.compareTo(((StringObject) other).value);
    }

    @Override
public String toString() {
  return  value;
}

}