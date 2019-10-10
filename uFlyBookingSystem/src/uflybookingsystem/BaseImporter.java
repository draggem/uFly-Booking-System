/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem;

/**
 *
 * @author Handel
 */
public abstract class BaseImporter implements Runnable {
    String fileName;
    ImportResult results;
    
    public void setResults(ImportResult results){
        this.results = results;
    }
    
    public ImportResult getResult(){
        return results;
    }
    
    /**
     *
     */
    @Override
    public abstract void run();
    
    public BaseImporter(String fileName){
        this.fileName = fileName;
    }
}
