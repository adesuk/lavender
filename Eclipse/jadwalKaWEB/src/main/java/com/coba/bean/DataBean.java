package com.coba.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
 
/**
 * Page Backing bean for example application.
 *
 * @author John Yeary
 * @version 1.0
 */
@ManagedBean
@ViewScoped
public class DataBean implements Serializable {
 
    private static final long serialVersionUID = -1776353555799643520L;
    private List<SelectItem> states;
    private Map<String, List<SelectItem>> cities;
    private String selectedState;
    private String selectedCity;
 
    /**
     * Default Constructor.
     */
    public DataBean() {
        states = new ArrayList<SelectItem>();
        cities = new HashMap<String, List<SelectItem>>();
    }
 
    /**
     * Initializes data.
     */
    @PostConstruct
    private void initialize() {
        states.add(new SelectItem("Maine"));
        states.add(new SelectItem("South Carolina"));
        states.add(new SelectItem("Illinois"));
 
        List<SelectItem> mcities = new ArrayList<SelectItem>();
        mcities.add(new SelectItem("Augusta"));
        mcities.add(new SelectItem("Bangor"));
        mcities.add(new SelectItem("Bath"));
        mcities.add(new SelectItem("Brunswick"));
        mcities.add(new SelectItem("Castine"));
        mcities.add(new SelectItem("Ellsworth"));
        mcities.add(new SelectItem("Portland"));
        mcities.add(new SelectItem("Woolwich"));
        cities.put("Maine", mcities);
 
        List<SelectItem> scities = new ArrayList<SelectItem>();
        scities.add(new SelectItem("Charleston"));
        scities.add(new SelectItem("Clemson"));
        scities.add(new SelectItem("Columbia"));
        scities.add(new SelectItem("Greenville"));
        scities.add(new SelectItem("Simpsonville"));
        cities.put("South Carolina", scities);
 
        List<SelectItem> icities = new ArrayList<SelectItem>();
        icities.add(new SelectItem("Argonne"));
        icities.add(new SelectItem("Batavia"));
        icities.add(new SelectItem("Chicago"));
        icities.add(new SelectItem("Evanston"));
        cities.put("Illinois", icities);
    }
 
    /**
     * Gets the selected city.
     *
     * @return selected city.
     */
    public String getSelectedCity() {
        return selectedCity;
    }
 
    /**
     * Sets the selected city.
     *
     * @param selectedCity city to be set.
     */
    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }
 
    /**
     * Gets selected state.
     *
     * @return selected state.
     */
    public String getSelectedState() {
        return selectedState;
    }
 
    /**
     * Sets the selected state.
     *
     * @param selectedState state to be set.
     */
    public void setSelectedState(String selectedState) {
        this.selectedState = selectedState;
    }
 
    /**
     * Gets a {@code List<SelectItem>} for populating state names.
     *
     * @return a list of state names.
     */
    public List<SelectItem> getStates() {
        return states;
    }
 
    /**
     * Gets a {@code List<SelectItem>} of cities based on the selected state.
     *
     * @return a list of cities based on selected state, or an empty list if no
     * state is selected.
     */
    public List<SelectItem> getCities() {
        if (selectedState != null) {
            return cities.get(selectedState);
        } else {
            return new ArrayList<SelectItem>();
        }
    }
 
    /**
     * This listener cleans up the city value if a new state is selected.
     *
     * @param event a change event when the value of the state changes.
     */
    public void stateChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != selectedState) {
            selectedCity = null;
        }
    }
}
