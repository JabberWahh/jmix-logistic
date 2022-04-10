package org.lightspeedsnail.service;

import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import org.lightspeedsnail.entity.EntityEnumerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberingService {

    @Autowired
    private DataManager dataManager;

    public String getLastNumber(String entityName){

        EntityEnumerator ee = dataManager.load(EntityEnumerator.class)
                .query("select ee from EntityEnumerator ee where ee.entityName = ?1", entityName)
                .optional()
                .orElse(dataManager.create(EntityEnumerator.class));

        if(ee.getNumber() == 0){
            ee.setEntityName(entityName);
        }
        Integer last = ee.getNumber() + 1;
        ee.setNumber(last);
        dataManager.save(ee);

        return String.format("%08d", last);
    }

}
