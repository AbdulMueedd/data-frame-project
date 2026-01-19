public class CharacterObject implements DataObject {
  private char value; // initialize char value

  public CharacterObject(char value) {
    this.value = value; 
  }

  @Override
  public Character getValue() {
    return value; //getter for value
  }

  @Override
  public void setValue(Object value) { //setter for value 
    if (value instanceof Character) {
      this.value = (Character) value; 
    } else {
      throw new IllegalArgumentException("Invalid value type for CharacterObject.");
    }
  }

  @Override
  public int compareTo(DataObject other) {
    if (!(other instanceof CharacterObject)) { // can only compare to another charElement, otherwise must throw an illegal argument exception (not same type)
      throw new IllegalArgumentException(
          "Invalid comparison between CharacterObject and " + other.getClass().getSimpleName());
    }
    return Character.compare(this.value, ((CharacterObject) other).value);
  }

  @Override
public String toString() {
  return "CharacterObject: " + value;
}

}