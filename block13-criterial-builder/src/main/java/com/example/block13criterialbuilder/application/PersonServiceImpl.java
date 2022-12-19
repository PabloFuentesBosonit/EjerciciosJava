package com.example.block13criterialbuilder.application;

import com.example.block13criterialbuilder.controller.dto.PersonInputDto;
import com.example.block13criterialbuilder.controller.dto.PersonMapper;
import com.example.block13criterialbuilder.controller.dto.PersonOutputDto;
import com.example.block13criterialbuilder.domain.Person;
import com.example.block13criterialbuilder.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

import static com.example.block13criterialbuilder.controller.PersonController.*;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public PersonOutputDto addPerson(PersonInputDto person) {
        Person persona = PersonMapper.Instance.personInputDTOToPerson(person);
        Person personDb = personRepository.save(persona);
        return PersonMapper.Instance.personToPersonOutputDTO(personDb);
    }

    @Override
    public PersonOutputDto getPersonById(int id) {
        Optional<Person> person = personRepository.findById(id);
        return PersonMapper.Instance.personToPersonOutputDTO(person.get());
    }

    @Override
    public PersonOutputDto getPersonByName(String name) {
        Optional<Person> person = personRepository.findByName(name);
        return PersonMapper.Instance.personToPersonOutputDTO(person.get());
    }


    @Override
    public void deletePersonById(int id) {
        personRepository.findById(id).orElseThrow();
        personRepository.deleteById(id);
    }
    @Override
    public List<PersonOutputDto> getAllPersons(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository
                .findAll()
                .stream()
                .map(person -> PersonMapper.Instance.personToPersonOutputDTO(person)).toList();
    }

    @Override
    public PersonOutputDto updatePerson(PersonInputDto person, int id) throws Exception {
        Optional<Person> personaDb = personRepository.findById(id);
        Person personInput = PersonMapper.Instance.personInputDTOToPerson(person);
        Boolean isEqual = Objects.equals(personaDb, personInput);
        if(isEqual){
            throw new Exception("Los campos introducidos no son compatibles");
        }
        personRepository.save(personInput);
        return PersonMapper.Instance.personToPersonOutputDTO(personInput);
    }

    @Override
    public Page<PersonOutputDto> allPersonByPage(int page, int pageSize){
        Page<Person> personaPages = personRepository.findAll(PageRequest.of(page, pageSize));
        return personaPages
                .map(PersonMapper.Instance::personToPersonOutputDTO);
    }

    @Override
    public List<PersonOutputDto> getCondition(HashMap<String, Object> conditions){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field,value) -> {
            switch (field){
                case "usuario":
                    predicates.add(cb.like(root.get(field), "%"+ (String) value + "%"));
                    break;
                case "name":
                    predicates.add(cb.like(root.get(field), "%"+ (String) value + "%"));
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field), "%"+ (String) value + "%"));
                    break;
                case "created":
                    String dateCondition=(String) conditions.get("dateCondition");
                    switch (dateCondition) {
                        case GREATER_THAN:
                            predicates.add(cb.greaterThan(root.<Date>get(field),(Date)value));
                            break;
                        case LESS_THAN:
                            predicates.add(cb.lessThan(root.<Date>get(field),(Date)value));
                            break;
                        case EQUAL:
                            predicates.add(cb.equal(root.<Date>get(field),(Date)value));
                            break;
                    }
                    break;
            }

            if(!Objects.isNull(field) && field.equals("order")){
                switch (value.toString()){
                    case "usuario":
                        query.orderBy(cb.desc(root.get("usuario")));
                        break;
                    case "name":
                        query.orderBy(cb.desc(root.get("name")));
                        break;
                }
            }
        });

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager
                .createQuery(query)
                .getResultList()
                .stream()
                .map(person -> PersonMapper.Instance.personToPersonOutputDTO(person)).toList();
    }
}

