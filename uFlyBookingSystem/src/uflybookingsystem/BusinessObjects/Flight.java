/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem.BusinessObjects;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 92017492
 */
public class Flight {
    
    //Set up the Date
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
    
    //Variables needed
    private String flightNumber;
    private String DepartureAirport;
    private String DestinationAirport;
    private double price;
    private Date dateTime;   //Might need to change to Date or Calendar
    private String plane;
    private int seatsTaken;

    /**
     * @return the flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @param flightNumber the flightNumber to set
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * @return the DepartureAirport
     */
    public String getDepartureAirport() {
        return DepartureAirport;
    }

    /**
     * @param DepartureAirport the DepartureAirport to set
     */
    public void setDepartureAirport(String DepartureAirport) {
        this.DepartureAirport = DepartureAirport;
    }

    /**
     * @return the DestinationAirport
     */
    public String getDestinationAirport() {
        return DestinationAirport;
    }

    /**
     * @param DestinationAirport the DestinationAirport to set
     */
    public void setDestinationAirport(String DestinationAirport) {
        this.DestinationAirport = DestinationAirport;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the plane
     */
    public String getPlane() {
        return plane;
    }

    /**
     * @param plane the plane to set
     */
    public void setPlane(String plane) {
        this.plane = plane;
    }

    /**
     * @return the seatsTaken
     */
    public int getSeatsTaken() {
        return seatsTaken;
    }

    /**
     * @param seatsTaken the seatsTaken to set
     */
    public void setSeatsTaken(int seatsTaken) {
        this.seatsTaken = seatsTaken;
    }
    
    
    
    
    @Override
    public String toString(){
        String date = df.format(dateTime);
        return date;
    }
}
