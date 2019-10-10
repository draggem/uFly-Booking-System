/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem;

import java.io.*;
import java.util.Arrays;
import uflybookingsystem.BusinessObjects.*;

/**
 *
 * @author Handel
 */
public class LocationImporter extends BaseImporter{

    public LocationImporter(String fileName) {
        super(fileName);
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
            if (columns.length == 2){
                if (lines[0].contains(columns[0]) && lines[0].contains(columns[1])){
                    lines = Arrays.copyOfRange(lines, 1, lines.length);
                    
                }
            }
            
            //Set totalRows
            int lineNum=1;
            for(String line: lines){
                try{
                    if (!line.equals("") && !line.isEmpty()){
                        results.setTotalRows(results.getTotalRows() + 1);
                        columns = line.split(",");

                        
                        if (columns.length != 2){
                           results.setFailedRows(results.getFailedRows() + 1);
                           results.errorMessages.add("Error importing location: Location columns have 2 only \r\nError at line " + lineNum);
                           continue;
                        }if (columns[0].equals("") || columns[0].isEmpty()){
                           results.setFailedRows(results.getFailedRows() + 1);
                           results.errorMessages.add("Error importing location: City column is Empty \r\nError at line " + lineNum);
                           continue;
                        }if(columns[1].equals("") || columns[1].isEmpty()){
                           results.setFailedRows(results.getFailedRows() + 1);
                           results.errorMessages.add("Error importing location: Airport Code column is empty \r\nError at line " + lineNum);
                           continue;
                        }if(!columns[1].matches("^[a-zA-Z]{3}$")){
                           results.setFailedRows(results.getFailedRows() + 1);
                           results.errorMessages.add("Error importing location: Airport Code does not match Code format\r\nError at line " + lineNum);
                        }
                        else{
                            results.setImportedRows(results.getImportedRows() + 1);
                            Location locationToUpdate;
                            Location locationToAdd = new Location();
                            locationToUpdate = DatabaseOperations.getLocationByAirportCode(columns[1]);
                            if (locationToUpdate == null){
                                
                                locationToAdd.setCity(columns[0]);
                                locationToAdd.setAirportCode(columns[1]); 
                                DatabaseOperations.AddLocation(locationToAdd);
                            }else{
                                locationToUpdate.setCity(columns[0]);
                                DatabaseOperations.UpdateLocation(locationToUpdate);
                            }
                        }

                    }      
                    
                    else{
                        System.out.println("No Need To Import.");
                    }
                }catch(Exception e){
                    results.errorMessages.add("Unknown Error Occured.");
                    System.out.println(e);
                }finally{
                    lineNum++;
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
