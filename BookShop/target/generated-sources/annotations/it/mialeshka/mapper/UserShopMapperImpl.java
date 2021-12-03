package it.mialeshka.mapper;

import it.mialeshka.dto.UserShopDto;
import it.mialeshka.entity.UserShop;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-30T12:39:52-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_31 (Oracle Corporation)"
)
@Component
public class UserShopMapperImpl implements UserShopMapper {

    @Override
    public UserShopDto toUserShopDto(UserShop userShop) {
        if ( userShop == null ) {
            return null;
        }

        UserShopDto userShopDto = new UserShopDto();

        userShopDto.setIdUser( userShop.getIdUser() );
        userShopDto.setUsername( userShop.getUsername() );
        userShopDto.setName( userShop.getName() );

        userShopDto.setPassword( "" );

        return userShopDto;
    }

    @Override
    public UserShop toUserShop(UserShopDto userShopDto) {
        if ( userShopDto == null ) {
            return null;
        }

        UserShop userShop = new UserShop();

        userShop.setIdUser( userShopDto.getIdUser() );
        userShop.setUsername( userShopDto.getUsername() );
        userShop.setName( userShopDto.getName() );
        userShop.setPassword( userShopDto.getPassword() );

        return userShop;
    }
}
