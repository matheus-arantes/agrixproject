package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.PersonCreationDto;
import com.betrybe.agrix.controller.dto.PersonDto;
import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Person Controller.
 */

@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping
  public ResponseEntity<PersonDto> createPerson(@RequestBody PersonCreationDto person) {
    Person newPerson = personService.create(person.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(PersonDto.toDto(newPerson));
  }
}
