package br.com.programadorbr.services;

import br.com.programadorbr.controllers.PersonController;
import br.com.programadorbr.data.vo.v1.PersonVO;
import br.com.programadorbr.data.vo.v2.PersonVOV2;
import br.com.programadorbr.exceptions.ResourceNotFoundException;
import br.com.programadorbr.mapper.DozerMapper;
import br.com.programadorbr.mapper.custom.PersonMapper;
import br.com.programadorbr.model.Person;
import br.com.programadorbr.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper personMapper;

    public PersonVO findById(Long id) {
        logger.info("Finding one person");

        var entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll() {
        var persons =  DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating one person");

        var entity = DozerMapper.parseObject(person, Person.class);
        PersonVO vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Creating one person with V2");

        var entity = personMapper.convertVoToEntity(person);
        return personMapper.convertEntityToVo(repository.save(entity));
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating one person");

        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one person");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }

}
