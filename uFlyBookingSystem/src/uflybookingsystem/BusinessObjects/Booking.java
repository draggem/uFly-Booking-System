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
public class Booking {
    
    private int bookingNumber;
    private String flightNumber;
    private double price;
    private String cabinClass;
    private int quantity;
    private boolean isInsured;

    /**
     * @return the bookingNumber
     */
    public int getBookingNumber() {
        return bookingNumber;
    }

    /**
     * @param bookingNumber the bookingNumber to set
     */
    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

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
     * @return the cabinClass
     */
    public String getCabinClass() {
        return cabinClass;
    }

    /**
     * @param cabinClass the cabinClass to set
     */
    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the isInsured
     */
    public boolean isIsInsured() {
        return isInsured;
    }

    /**
     * @param isInsured the isInsured to set
     */
    public void setIsInsured(boolean isInsured) {
        this.isInsured = isInsured;
    }
}
