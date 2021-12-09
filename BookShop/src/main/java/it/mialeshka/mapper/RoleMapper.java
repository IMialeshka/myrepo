package it.mialeshka.mapper;

import it.mialeshka.dto.RoleDto;
import it.mialeshka.entity.Role;
import it.mialeshka.entity.UserShop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    public RoleDto toRoleToRoleDto(Role role);
    public Role toRoleDtoToRole(RoleDto roleDto);
}
