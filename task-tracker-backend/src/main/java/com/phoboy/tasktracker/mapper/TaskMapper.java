package com.phoboy.tasktracker.mapper;

import com.phoboy.tasktracker.dto.TaskResponseDto;
import com.phoboy.tasktracker.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponseDto toDto(Task task);

}
