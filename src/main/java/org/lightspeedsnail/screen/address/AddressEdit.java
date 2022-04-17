package org.lightspeedsnail.screen.address;

import io.jmix.core.DataManager;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import org.lightspeedsnail.entity.Address;
import org.lightspeedsnail.entity.USZip;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@UiController("Address.edit")
@UiDescriptor("address-edit.xml")
@EditedEntityContainer("addressDc")
public class AddressEdit extends StandardEditor<Address> {
    @Autowired
    private DataManager dataManager;
    @Autowired
    private SuggestionField<String> countyField;
    @Autowired
    private SuggestionField<String> cityField;
    @Autowired
    private SuggestionField<String> stateField;
    @Autowired
    private Label stateIdLabel;

    @Subscribe("zipField")
    public void onZipFieldEnterPress(TextInputField.EnterPressEvent event) {
        Integer zip = (Integer)event.getSource().getValue();
        if(zip != 0){
            Optional<USZip> usZipOptional = dataManager.load(USZip.class)
                    .query("select u from USZip u where u.zip=?1", zip)
                    .optional();

            if(usZipOptional.isPresent()){
                USZip uszip = usZipOptional.get();
                countyField.setValue(uszip.getCounty_name());
                cityField.setValue(uszip.getCity());
                stateField.setValue(uszip.getState_name());

                renewStateLabel();
            }
        }

    }

    private void renewStateLabel() {

        if(!stateField.isEmpty()){
            String state_id = dataManager.load(USZip.class)
                    .query("select u from USZip u where u.state_name like ?1", stateField.getValue())
                    .optional()
                    .map(usZip -> usZip.getState_id())
                    .orElse("n/a");

            stateIdLabel.setValue("<h3>" + state_id + "</h3>");
        }
    }

    @Subscribe
    public void onInit(InitEvent event) {
        renewStateLabel();
    }

    @Install(to = "cityField", subject = "searchExecutor")
    private List<String> cityFieldSearchExecutor(String searchString, Map<String, Object> searchParams) {
        if(!stateField.getValue().isEmpty()){
            List<String> usZip = dataManager.load(USZip.class)
                    .query("select u from USZip u where u.city like ?1 and u.state_name = ?2"
                            , "(?i)%" + searchString + "%"
                            , stateField.getValue())
                    .list()
                    .stream()
                    .distinct()
                    .map(USZip::getCity)
                    .distinct()
                    .collect(Collectors.toList());

            return usZip;
        }
        return null;
    }

    @Install(to = "stateField", subject = "searchExecutor")
    private List<String> stateFieldSearchExecutor(String searchString, Map<String, Object> searchParams) {
        List<String> usZip = dataManager.load(USZip.class)
                .query("select u from USZip u where u.state_name like ?1 or u.state_id like ?1"
                        , "(?i)%" + searchString + "%")
                .list()
                .stream()
                .map(USZip::getState_name)
                .distinct()
                .collect(Collectors.toList());

        return usZip;
    }

    @Install(to = "countyField", subject = "searchExecutor")
    private List<String> countyFieldSearchExecutor(String searchString, Map<String, Object> searchParams) {
        List<String> usZip = dataManager.load(USZip.class)
                .query("select u from USZip u where (u.county_name like ?1 or u.county_names_all like ?1) and u.state_name like ?2"
                        , "(?i)%" + searchString + "%"
                , stateField.isEmpty() ? "%" : stateField.getValue())
                .list()
                .stream()
                .map(USZip::getCounty_name)
                .distinct()
                .collect(Collectors.toList());

        return usZip;
    }

    @Subscribe("stateField")
    public void onStateFieldValueChange(HasValue.ValueChangeEvent<String> event) {
        System.out.println(event);
    }

    @Subscribe("stateField")
    public void onStateFieldValueChange1(HasValue.ValueChangeEvent event) {
        renewStateLabel();
    }

}