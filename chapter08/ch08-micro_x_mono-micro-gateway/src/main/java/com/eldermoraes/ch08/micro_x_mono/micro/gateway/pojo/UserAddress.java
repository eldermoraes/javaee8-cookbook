package com.eldermoraes.ch08.micro_x_mono.micro.gateway.pojo;

import java.io.Serializable;

/**
 *
 * @author eldermoraes
 */
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long idUser;
    
    private String street;
    
    private String number;
    
    private String city;
    
    private String zip;
    
    public UserAddress(){
        
    }
    
    public UserAddress(Long user, String street, String number, String city, String zip) {
        this.idUser = user;
        this.street = street;
        this.number = number;
        this.city = city;
        this.zip = zip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserAddress)) {
            return false;
        }
        UserAddress other = (UserAddress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eldermoraes.ch08.micro_x_mono.micro.gateway.UserAddress[ id=" + id + " ]";
    }

}
