package br.com.programadorbr.services;

import br.com.programadorbr.data.vo.v1.PersonVO;
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
        Person entity = mockPerson.mockEntity(1);

        when(personRepository.findById(entity.getId())).thenReturn(Optional.of(entity));

        var result = services.findById(entity.getId());
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
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
        Person entity = mockPerson.mockEntity(1);
        PersonVO personVO = mockPerson.mockVO(1);
        when(personRepository.save(entity)).thenReturn(entity);

        var result = services.create(personVO);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void update() {
        Person entity = mockPerson.mockEntity(1);
        PersonVO personVO = mockPerson.mockVO(1);

        when(personRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(personRepository.save(entity)).thenReturn(entity);

        var result = services.update(personVO);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void delete() {
        Person entity = mockPerson.mockEntity(1);
        when(personRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        services.delete(1L);
    }
}