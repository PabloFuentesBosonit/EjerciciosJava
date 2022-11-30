package com.example.block7crudvalidation.subject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectInputDto {
    String subjectName;
    String comment;
}
