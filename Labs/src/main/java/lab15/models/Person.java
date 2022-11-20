package lab15.models;

public class Person {
    
    private String name;
    private int age;
    private String email;
    private long socialsecurity;
    private Address address;
    
    public Person() {
    }

    public Person(String name, int age, String email, long socialsecurity, Address address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.socialsecurity = socialsecurity;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getSocialsecurity() {
        return socialsecurity;
    }

    public void setSocialsecurity(long socialsecurity) {
        this.socialsecurity = socialsecurity;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person [address=" + address + ", age=" + age + ", email=" + email + ", name=" + name
                + ", socialsecurity=" + socialsecurity + "]";
    }    
}