package com.example.cooktogether.data.repositories;

import com.example.cooktogether.data.Service.PersonService;
import com.example.cooktogether.data.models.Person;

import java.util.List;

public class PersonRepository {
    private PersonService personService;
    
    public PersonRepository(){
        this.personService = new PersonService();

    }

    public List<Person> getData() {
        return personService.person();
    }
}
