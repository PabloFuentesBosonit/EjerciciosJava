package com.example.block7crudvalidation.subject.controller.dto;

import com.example.block7crudvalidation.subject.domain.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMapper {

    SubjectMapper Instance = Mappers.getMapper(SubjectMapper.class);

    SubjectOutputDto subjectToSubjectOutputDTO(Subject subject);

    Subject subjectInputDTOToSubject(SubjectInputDto subjectInputDto);
}
