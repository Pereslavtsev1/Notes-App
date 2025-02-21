package com.example.notes_app.mapper;

import com.example.notes_app.dto.ApplicationUserDto;
import com.example.notes_app.model.ApplicationUser;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationUserMapper extends Mappable<ApplicationUser, ApplicationUserDto> {

}
