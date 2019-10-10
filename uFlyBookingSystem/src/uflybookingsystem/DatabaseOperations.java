/*
 * This class contains all the common operations that involve retreiving data from the database,
 * saving data to the database or updating existing database data
 * for all three tables (Location, Flight and Booking)
 */
package uflybookingsystem;

import uflybookingsystem.BusinessObjects.Flight;
import uflybookingsystem.BusinessObjects.Booking;
import uflybookingsystem.BusinessObjects.Location;
import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;




public class DatabaseOperations {
    
    

    //method that gets all the information from the Location table
    public static ArrayList<Location> GetAllLocations(){
        ArrayList<Location> locations = new ArrayList();
        
        try(Connection connection = DbConnector.connectToDb();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM locations")){
            while (resultSet.next()){
                Location location = new Location();
                location.setCity(resultSet.getString(1));
                location.setAirportCode(resultSet.getString(2));
                locations.add(location);
            }
        }catch(SQLException sqle){
            System.out.println(sqle);
        }

        return locations;
    }

    // this method returns all the data from the Flight table in the uFly database
   // public static ArrayList<Flight> GetAllFlights(String departure, String destination){

    //}

    //this method obtains all the information from the Flight table based on the departure and destination airports as well as travel date
    public static ArrayList<Flight> GetAllFlightsForLocation(String departure, String destination){
        ArrayList<Flight> allFlights = new ArrayList();
        

        try (Connection connection = DbConnector.connectToDb();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM flight WHERE DepartureAirport='" + departure +"' AND DestinationAirport='" + destination + "'")){
            
            while(resultSet.next()){
                    Flight flight = new Flight();
                    flight.setFlightNumber(resultSet.getString(1));
                    flight.setDepartureAirport(resultSet.getString(2));
                    flight.setDestinationAirport(resultSet.getString(3));
                    flight.setPrice(resultSet.getDouble(4));
                    flight.setDateTime(resultSet.getTimestamp(5));
                    flight.setPlane(resultSet.getString(6));
                    flight.setSeatsTaken(resultSet.getInt(7));
                allFlights.add(flight);

            }
        }catch(SQLException sqle){
            System.out.println(sqle);
        }
        return allFlights;
    }

    //this method adds booking passed as a parameter to the Booking table in the uFly database
    //note that Booking number is set as an incrementing field, so it doesn't need to be set
    public static void AddBooking(Booking booking){
        try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT * from booking")){

            resultSet.moveToInsertRow();
            resultSet.updateString("FlightNumber", booking.getFlightNumber());
            resultSet.updateDouble("Price", booking.getPrice());
            resultSet.updateString("CabinClass", booking.getCabinClass());
            resultSet.updateInt("Quantity", booking.getQuantity());
            resultSet.updateBoolean("Insurance", booking.isIsInsured());
            resultSet.insertRow();
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        }
    }

    //this method obtains the flight based on the flightNumber parameter
    public static Flight getFlightByFLightNumber(String flightNumber){
        
        try(Connection connection = DbConnector.connectToDb();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * from flight WHERE FlightNumber='" + flightNumber +"'")){
            while (resultSet.next()){
                Flight flight = new Flight();                
                    flight.setFlightNumber(flightNumber);
                    flight.setDepartureAirport(resultSet.getString(2));
                    flight.setDestinationAirport(resultSet.getString(3));
                    flight.setPrice(resultSet.getDouble(4));
                    flight.setDateTime(resultSet.getTimestamp(5));
                    flight.setPlane(resultSet.getString(6));
                    flight.setSeatsTaken(resultSet.getInt(7));
                    return flight;
            }
        }catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }


     //this method obtains the flight based on the flightNumber parameter
   public static Location getLocationByAirportCode(String airportCode){
       
	try (Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from locations WHERE AirportCode='"  + airportCode +"'")){
            
            while (resultSet.next()) {
            	Location location = new Location();
            	location.setCity(resultSet.getString("City"));
            	location.setAirportCode(resultSet.getString("AirportCode"));
            	return location;
            }
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        }
        return null;
    }

    //this method adds location passed as a parameter to the Location table in the uFly database
    public static void AddLocation(Location location){
	try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT City, AirportCode from Locations")){

            resultSet.moveToInsertRow();
            resultSet.updateString("City", location.getCity());
            resultSet.updateString("AirportCode", location.getAirportCode());
            resultSet.insertRow();
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        }
    }

    //this method adds a flight passed as a parameter to the Flight table in the uFly database
    public static void AddFlight(Flight flight){

        try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT * from flight")){
            System.out.println(flight.getDateTime());
            resultSet.moveToInsertRow();
            resultSet.updateString("FlightNumber", flight.getFlightNumber());
            resultSet.updateString("DepartureAirport", flight.getDepartureAirport());
            resultSet.updateString("DestinationAirport", flight.getDestinationAirport());
            resultSet.updateDouble("Price", flight.getPrice());
            resultSet.updateTimestamp("DateTime", new java.sql.Timestamp(flight.getDateTime().getTime()));
            resultSet.updateString("Plane", flight.getPlane());
            resultSet.updateInt("SeatsTaken", flight.getSeatsTaken());
            resultSet.insertRow();
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        }
    }


    //this method updates the location to the one passed to it as a parameter where the airport codes are matching
    public static void UpdateLocation(Location location){
        String updateLocationQuery = "UPDATE locations SET City=? WHERE AirportCode ='" + location.getAirportCode()+"'";
        try(Connection connection = DbConnector.connectToDb();
                PreparedStatement statement = connection.prepareStatement(updateLocationQuery)){

                statement.setString(1, location.getCity()); //only need to update the city name 
                //statement.setString(2, location.getAirportCode());

                statement.executeUpdate();

        }catch(Exception e){
            System.out.println(e);
        }
    }

    //this method updates the flight to the one passed to it as a parameter where the flight numbers are matching
    public static void UpdateFlight(Flight flight){
        String updateFlightQuery = "UPDATE flight SET FlightNumber=?,DepartureAirport=?,DestinationAirport=?,Price=?,DateTime=?,Plane=?,SeatsTaken=?  WHERE FlightNumber='" + flight.getFlightNumber()+"'";
        try(Connection connection = DbConnector.connectToDb();
                PreparedStatement statement = connection.prepareStatement(updateFlightQuery)){
                
                statement.setString(1, flight.getFlightNumber());
                statement.setString(2, flight.getDepartureAirport());
                statement.setString(3, flight.getDestinationAirport());
                statement.setDouble(4, flight.getPrice());
                statement.setTimestamp(5, new java.sql.Timestamp(flight.getDateTime().getTime()));
                statement.setString(6, flight.getPlane());
                statement.setInt(7, flight.getSeatsTaken());
                
                statement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
