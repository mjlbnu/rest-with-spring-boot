package br.com.programadorbr.repositories;

import br.com.programadorbr.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
