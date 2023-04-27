package com.example.cooktogether.data.Service;

import com.example.cooktogether.data.models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
    public List<Person> person(){
        ArrayList<Person> persons = new ArrayList<>();
        for (int i = 0; i < 42;i++){
            persons.add(new Person("John " + (i + 1)));
        }
        return persons;
    }
}
