package it.mialeshka.mapper;

import it.mialeshka.dto.RoleDto;
import it.mialeshka.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toRoleToRoleDto(Role role);
    Role toRoleDtoToRole(RoleDto roleDto);
}
