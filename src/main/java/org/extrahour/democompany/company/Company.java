package org.extrahour.democompany.company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "company")
public class Company {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;
  @NotEmpty
  private String name;
  @NotEmpty
  private String address;
  @NotEmpty
  private String city;
  @NotEmpty
  private String country;
  private String emailAddress;
  private String phoneNumber;

  public Company() {
  }

  public Company(String name,
      String address,
      String city,
      String country,
      String emailAddress,
      String phoneNumber) {
    this.name = name;
    this.address = address;
    this.city = city;
    this.country = country;
    this.emailAddress = emailAddress;
    this.phoneNumber = phoneNumber;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String toString() {
    return "CompanyEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", address='" + address + '\'' +
        ", city='" + city + '\'' +
        ", country='" + country + '\'' +
        ", emailAddress='" + emailAddress + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        '}';
  }
}
