package org.usfirst.frc.team5740.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opencsv.CSVWriter;

import org.usfirst.frc.team5740.Main;

/**
 * This Class Creates And Manages Csv Path Files for use on the Robot
 * 
 * @author Nicholas Blackburn
 */
public class GenerateCsv {
    private CSVWriter csvWriter;
    private FileWriter outputFile;
    private File file;
    

    private String wayId;
    private String wayX;
    private String wayY;
    private String wayTheta;
    private String wayMaxAcc;
    private String wayMaxJerk;
    private String wayMaxVelocity;
    private String wayDt;

    private String[] header ={"id","x","y","theta","maxAcc","maxJerk","maxVelocity","dt"};
    

    /**
     * Creates Csv File For Storing PathData
     * @param Path
     */
    public void createCsv(String filePath){
        try{
            Main.logger.info("creating file " + filePath);
            file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            Main.logger.info("created file " + filePath);
            csvWriter = new CSVWriter(outputFile);
            csvWriter.writeNext(header);
        }catch(Exception e){
            Main.logger.info(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This Function adds waypoint cals to file 
     * @exception Poor way of converting strings to and String array to insert data
     * @param id
     * @param x
     * @param y
     * @param theta
     * @param maxAcc
     * @param macJerk
     * @param mscVelocity
     * @param dt
     */
    public void addData(int id,double x , double y, double theta, double maxAcc, double maxJerk, double maxVelocity, double dt){
        wayId.valueOf(id);
        wayX.valueOf(x);
        wayY.valueOf(y);
        wayTheta.valueOf(theta);
        wayMaxAcc.valueOf(maxAcc);
        wayMaxJerk.valueOf(maxJerk);
        wayMaxVelocity.valueOf(maxVelocity);
        wayDt.valueOf(dt);

        String[] data = {wayId,wayX,wayY,wayTheta,wayMaxAcc,wayMaxJerk,wayMaxVelocity,wayDt};
        try{
            csvWriter.writeNext(data);
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }

}