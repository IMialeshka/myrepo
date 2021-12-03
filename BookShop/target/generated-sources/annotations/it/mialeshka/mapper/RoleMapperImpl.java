package it.mialeshka.mapper;

import it.mialeshka.dto.RoleDto;
import it.mialeshka.entity.Role;
import it.mialeshka.entity.UserShop;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-30T12:39:52-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_31 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto toRoleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setName( role.getName() );
        List<UserShop> list = role.getUsers();
        if ( list != null ) {
            roleDto.setUsers( new ArrayList<UserShop>( list ) );
        }

        return roleDto;
    }

    @Override
    public Role toRoleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setName( roleDto.getName() );
        List<UserShop> list = roleDto.getUsers();
        if ( list != null ) {
            role.setUsers( new ArrayList<UserShop>( list ) );
        }

        return role;
    }
}
