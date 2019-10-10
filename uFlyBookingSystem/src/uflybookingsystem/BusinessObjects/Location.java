/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem.BusinessObjects;

/**
 *
 * @author 92017492
 */
public class Location {
    
    private String airportCode;
    private String city;

    /**
     * @return the airportCode
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * @param airportCode the airportCode to set
     */
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    @Override
    public String toString(){
        return city + " " + airportCode;
    }
}
