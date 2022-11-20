package lab15.models;

public class Address {
    
    private String addressLineOne;
    //Example (APT Number)
    private String addressLineTwo;
    private String city;
    private String state;
    private int zip;
    private String country;

    public Address(){

    }

    public Address(String lineOne, String city, String state, int zip, String country){
        this.addressLineOne = lineOne;
        this.addressLineTwo = "";
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public Address(String lineOne, String lineTwo, String city, String state, int zip, String country){
        this.addressLineOne = lineOne;
        this.addressLineTwo = lineTwo;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address [addressLineOne=" + addressLineOne + ", addressLineTwo=" + addressLineTwo + ", city=" + city
                + ", country=" + country + ", state=" + state + ", zip=" + zip + "]";
    }
}