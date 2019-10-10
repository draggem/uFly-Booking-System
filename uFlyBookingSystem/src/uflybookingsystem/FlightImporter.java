/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import uflybookingsystem.BusinessObjects.Flight;
import uflybookingsystem.BusinessObjects.Plane;

/**
 *
 * @author Owner
 */
public class FlightImporter extends BaseImporter{
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
    
    Plane planeType;
    public FlightImporter(String fileName) {
        super(fileName);
    }
    
    //Look for planeType in Enum Planes
    public boolean findPlaneType(String planeTypeFromDb){
        boolean typeIsTrue = false;
        ArrayList<String> planeTypes = new ArrayList();
        for(Plane plane: Plane.values()){       
            planeTypes.add(String.valueOf(plane));
            
        }
        
        for(String type: planeTypes){
            
            if (planeTypeFromDb.equals(type)){
                planeType = Plane.valueOf(planeTypeFromDb);
                typeIsTrue = true;
                break;
            }else{
                typeIsTrue = false;
            }
        }
        return typeIsTrue;   
    }
    
    @Override
    public void run() {
        //Instantiate the results of importing
        ImportResult results = new ImportResult();

        
        
        int c;
        String firstLine;
        String fileData = "";
        try(FileReader inputStream = new FileReader(fileName)){ 
            
            
            //stores the textfile lines in a variable
            while ((c = inputStream.read()) != -1) {
                fileData += String.valueOf((char)c);
            }  
  
            //stores each city/airport code as an array (sorted with regex)
            String[] lines = fileData.replace("\r\n", "\n").replace("\r","\n").split("\n");
            
            
            
            firstLine = lines[0];
            String [] columns = firstLine.split(",");
            //remove column names
            if (columns.length == 7){
                for (int i=1; i<7; i++){
                    if (lines[0].contains(columns[i])){
                        lines = Arrays.copyOfRange(lines, 1, lines.length);
                    }
                }
            }
            
            
            
            
            //Set totalRows
            int lineNum=1;
            
            for(String line: lines){
                try{
                    if (!line.equals("") && !line.isEmpty()){
                        results.setTotalRows(results.getTotalRows() + 1);
                        columns = line.split(",");
                        
                        if (columns.length != 7){
                           results.setFailedRows(results.getFailedRows() + 1);
                           results.errorMessages.add("Error importing flight: Flight columns have 7 \r\nError at line " + lineNum);
                           continue;
                        }if (columns[0].equals("") || columns[0].isEmpty()){
                           results.setFailedRows(results.getFailedRows() + 1);
                           results.errorMessages.add("Error importing flight: FLight Number column is Empty \r\nError at line " + lineNum);
                           continue;
                        }if(columns[1].equals("") || columns[1].isEmpty()){
                           results.setFailedRows(results.getFailedRows() + 1);
                           results.errorMessages.add("Error importing flight: Departure column is empty \r\nError at line " + lineNum);
                           continue;
                        }if(columns[2].equals("") || columns[2].isEmpty()){
                            results.setFailedRows(results.getFailedRows() + 1);
                            results.errorMessages.add("Error importing flight: Destination column is empty \r\nError at line " + lineNum);
                            continue;
                            
                        }if(columns[5].equals("") || columns[5].isEmpty()){
                            System.out.println("testing if plane is empty");
                            results.setFailedRows(results.getFailedRows() + 1);
                            results.errorMessages.add("Error importing Flight: Plane field is empty \r\nError at line " + lineNum);
                            continue;
                        }                              //or [0-9]{3} if \\d not working
                        if(!columns[0].matches("^[A-Za-z]{2}\\d{3}$")){ 
                            
                           results.setFailedRows(results.getFailedRows() + 1);
                           results.errorMessages.add("Error importing flight: Flight Number does not match Code format\r\nError at line " + lineNum);
                           continue;
                        }
                        if(!columns[1].matches("^[a-zA-Z]{3}$")){
                           results.setFailedRows(results.getFailedRows() + 1);
                           results.errorMessages.add("Error importing flight: Airport Code does not match Code format\r\nError at line " + lineNum);
                           continue;
                        }if(!columns[2].matches("^[a-zA-Z]{3}$")){
                           results.setFailedRows(results.getFailedRows() + 1);
                           results.errorMessages.add("Error importing location: Airport Code does not match Code format\r\nError at line " + lineNum);
                           continue;
                        }if(!columns[3].matches("^\\d+{2}$")){
                            results.setFailedRows(results.getFailedRows() + 1);
                            results.errorMessages.add("Error importing flight: Price does not match Code format\r\nError at line " + lineNum);
                            continue;
                        }if(!columns[6].matches("^\\d+$")){
                            results.setFailedRows(results.getFailedRows() + 1);
                            results.errorMessages.add("Error importing flight: Seats taken does not match Code format\r\nError at line " + lineNum);
                            continue;
                        }if(!columns[4].matches("^(((0[1-9]|[12]\\d|3[01])[\\/\\.-](0[13578]|1[02])[\\/\\.-]((19|[2-9]\\d)\\d{2})\\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((0[1-9]|[12]\\d|30)[\\/\\.-](0[13456789]|1[012])[\\/\\.-]((19|[2-9]\\d)\\d{2})\\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((0[1-9]|1\\d|2[0-8])[\\/\\.-](02)[\\/\\.-]((19|[2-9]\\d)\\d{2})\\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((29)[\\/\\.-](02)[\\/\\.-]((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))\\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])))$")){
                            //{^[0-3][0-9]/[0-1][0-9]/[0-9]{4} [0-2][0-9]:[0-5][0-9]:[0-5][0-9]&} regex not working
                            results.setFailedRows(results.getFailedRows() + 1);
                            results.errorMessages.add("Error importing flight: Date Time does not match Code format\r\nError at line " + lineNum);
                            continue;
                        }
                        if(!findPlaneType(columns[5])){
                            results.setFailedRows(results.getFailedRows() + 1);
                            results.errorMessages.add("Error importing flight: Plane type does not match \r\nError at line " + lineNum);
                            continue;
                        }if(Double.parseDouble(columns[6]) > planeType.getPassengerCapacity()){
                            results.setFailedRows(results.getFailedRows() + 1);
                            results.errorMessages.add("Error importing flight: Passenger Seats Exceeded\r\nError at line " + lineNum);
                        }
                        else{
                            results.setImportedRows(results.getImportedRows() + 1);
                            Flight flightToUpdate;
                            Flight flightToAdd = new Flight();
                            flightToUpdate = DatabaseOperations.getFlightByFLightNumber(columns[0]);
                            
                            if (flightToUpdate == null){
                                
                                flightToAdd.setFlightNumber(columns[0]);
                                flightToAdd.setDepartureAirport(columns[1]);
                                flightToAdd.setDestinationAirport(columns[2]);
                                flightToAdd.setPrice(Double.parseDouble(columns[3]));
                                //convert java.util.date to java.sql.date to be imported to database
                                Date date = df.parse(columns[4]);                                
                                //java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
                                flightToAdd.setDateTime(date);
                                flightToAdd.setPlane(planeType.toString());
                                flightToAdd.setSeatsTaken(planeType.getPassengerCapacity());
                                
                                DatabaseOperations.AddFlight(flightToAdd);
                            }else{
                                
                                flightToUpdate.setFlightNumber(columns[0]);
                                flightToUpdate.setDepartureAirport(columns[1]);
                                flightToUpdate.setDestinationAirport(columns[2]);
                                flightToUpdate.setPrice(Double.parseDouble(columns[3]));
                                Date date = df.parse(columns[4]);
                                flightToUpdate.setDateTime(date);
                                flightToUpdate.setPlane(planeType.toString());
                                flightToUpdate.setSeatsTaken(planeType.getPassengerCapacity());
                                DatabaseOperations.UpdateFlight(flightToUpdate);
                            }
                        }

                    }      
                    
                    else{
                        System.out.println("No Need To Import. It has empty Field");
                    }
                }catch(NumberFormatException | ParseException e){
                    results.errorMessages.add("Unknown Error Occured.");
                    System.out.println(e);
                }finally{
                    lineNum++;
                    for(String msg: results.errorMessages){
                    
                    System.out.println(msg);
                    }
                    
                }
                
                
            }
            
        }catch(Exception e){
            results.errorMessages.add("Input/Output Errors happened");
            System.out.println(e);
        }
        ImportFeedbackForm feedbackForm = new ImportFeedbackForm(results);
        feedbackForm.setVisible(true);
     }
    
}
