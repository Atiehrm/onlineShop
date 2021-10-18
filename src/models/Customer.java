package models;


import java.util.Objects;

public class Customer {
    private int id;
    private String fullName;
    private String nationalCode;
    private String email;

    public Customer(String fullName, String nationalCode, String email) {
        this.fullName = fullName;
        this.nationalCode = nationalCode;
        this.email = email;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(nationalCode, customer.nationalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalCode);
    }
}
