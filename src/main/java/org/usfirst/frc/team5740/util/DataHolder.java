package org.usfirst.frc.team5740.util;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class DataHolder {

    private StringProperty name = new SimpleStringProperty();
    private ObservableList list = new SimpleListProperty<>();
    
    public Object getXArray(){
        return list.toArray();
    }
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getName() {
		return name.get();
	}
	
	public StringProperty getNameProperty() {
		return name;
	}
}