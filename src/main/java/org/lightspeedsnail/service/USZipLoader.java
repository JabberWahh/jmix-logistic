package org.lightspeedsnail.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.security.SystemAuthenticator;
import org.lightspeedsnail.entity.USZip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class USZipLoader {

    Logger logger = LoggerFactory.getLogger(USZipLoader.class);
    @Autowired
    private Metadata metadata;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private SystemAuthenticator systemAuthenticator;


    @Autowired
    private CurrentAuthentication currentAuthentication;

    @ManagedOperation
    public void loadFromInnerCSV() {

        systemAuthenticator.withSystem(() -> {
            //Todo make with count
            Optional<USZip> zips = dataManager.load(USZip.class).all().optional();
            if (!zips.isPresent()) {

                int i = 0;
                try (CSVReader reader = new CSVReader(new FileReader("uszips.csv"))) {
                    String[] lineInArray;
                    reader.skip(1);
                    while ((lineInArray = reader.readNext()) != null) {
                        USZip usZip = metadata.create(USZip.class);
                        usZip.setZip(Integer.parseInt(lineInArray[0]));
                        usZip.setLat(Double.parseDouble((lineInArray[1])));
                        usZip.setLng(Double.parseDouble(lineInArray[2]));
                        usZip.setCity((lineInArray[3]));
                        usZip.setState_id(lineInArray[4]);
                        usZip.setState_name(lineInArray[5]);
                        usZip.setPopulation(Integer.parseInt(lineInArray[8].length() == 0 ? "0" : lineInArray[8]));
                        usZip.setCounty_fips(Integer.parseInt(lineInArray[10]));
                        usZip.setCounty_name(lineInArray[11]);
                        usZip.setCounty_names_all(lineInArray[13]);
                        usZip.setTimezone(lineInArray[17]);

                        dataManager.save(usZip);
                    }
                } catch (IOException | CsvValidationException e) {
                    logger.error("Error line " + i);
                }
                logger.info("Done!");
            }
            return "Done";
        });

    }

}
