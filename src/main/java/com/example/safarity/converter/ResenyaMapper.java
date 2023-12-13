package com.example.safarity.converter;


import com.example.safarity.dto.ResenyaDTO;
import com.example.safarity.model.Resenya;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResenyaMapper {

    Resenya toEntity(ResenyaDTO dto);

    ResenyaDTO toDTO(Resenya entity);

    List<Resenya> toEntity(List<ResenyaDTO> dtos);

    List<ResenyaDTO> toDTO(List<Resenya> entitys);


}
