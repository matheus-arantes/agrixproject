package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * Person Dto.
 */
public record PersonDto(Long id, String username, Role role) {

  /**
   * Converts an entity to dto.
   */

  public static PersonDto toDto(Person person) {
    return new PersonDto(
            person.getId(),
            person.getUsername(),
            person.getRole()
    );
  }
}
