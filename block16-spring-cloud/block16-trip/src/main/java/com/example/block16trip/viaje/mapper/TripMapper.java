package com.example.block16trip.viaje.mapper;

import com.example.block16trip.viaje.controller.dto.TripInputDto;
import com.example.block16trip.viaje.controller.dto.TripOutputDto;
import com.example.block16trip.viaje.domain.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TripMapper {
    TripMapper Instance = Mappers.getMapper(TripMapper.class);

    Trip tripInputDtoToTrip(TripInputDto tripInputDto);

    TripOutputDto tripToTripOutputDto(Trip trip);
}
