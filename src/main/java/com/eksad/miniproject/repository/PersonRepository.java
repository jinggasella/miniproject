package com.eksad.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eksad.miniproject.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
