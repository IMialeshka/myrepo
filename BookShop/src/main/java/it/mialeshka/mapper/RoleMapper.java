package it.mialeshka.mapper;

import it.mialeshka.dto.RoleDto;
import it.mialeshka.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    public RoleDto toRoleToRoleDto(Role role);
    public Role toRoleDtoToRole(RoleDto roleDto);
}
