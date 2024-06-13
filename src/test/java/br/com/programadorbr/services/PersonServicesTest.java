package br.com.programadorbr.services;

import br.com.programadorbr.mapper.mocks.MockPerson;
import br.com.programadorbr.model.Person;
import br.com.programadorbr.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Ciclo de vida do teste - uma instância por classe
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson mockPerson;

    @InjectMocks
    private PersonServices services;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        mockPerson = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = mockPerson.mockEntity(1);

        when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));

        var result = services.findById(person.getId());
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        //System.out.println(result.toString());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}