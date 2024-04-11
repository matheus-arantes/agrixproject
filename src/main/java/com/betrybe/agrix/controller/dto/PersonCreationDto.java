package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * Person Creation Dto.
 */

public record PersonCreationDto(String username, String password, Role role) {

  /**
   * Converts a dto to entity.
   */

  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);
    return person;
  }
}
