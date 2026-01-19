public interface DataObject { // calls objects and methods 
  Object getValue(); 

  void setValue(Object value);

  int compareTo(DataObject other);
}