package Pojc;

public class Variable {
    private String Name;
    private String Value;
    private String Remarks;
    private String ReflectClass;
    private String ReflectMethod;
    private String ReflectValue;

    public String getReflectClass() {
        return ReflectClass;
    }

    public void setReflectClass(String reflectClass) {
        ReflectClass = reflectClass;
    }

    public String getReflectMethod() {
        return ReflectMethod;
    }

    public void setReflectMethod(String reflectMethod) {
        ReflectMethod = reflectMethod;
    }

    public String getReflectValue() {
        return ReflectValue;
    }

    public void setReflectValue(String reflectValue) {
        ReflectValue = reflectValue;
    }

    public Variable() {
    }
    public String getName() {
        return Name;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public Variable(String name, String value, String remarks) {
        Name = name;
        Value = value;
        Remarks = remarks;
    }

    public String getValue() {
        return Value;
    }

    public void setName(String name) {
        Name = name;
    }
    public void setValue(String value) {
        Value = value;
    }
}
