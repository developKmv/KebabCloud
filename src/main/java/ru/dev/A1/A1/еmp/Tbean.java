package ru.dev.A1.A1.еmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//@Component
public class Tbean {
    public String testField;
    @Autowired
    public Tbean(@Qualifier("tstStr")String testField){
        this.testField = testField;
    }

    @Override
    public String toString(){
        return new String(String.format("testField %s",this.testField));
    }

}
