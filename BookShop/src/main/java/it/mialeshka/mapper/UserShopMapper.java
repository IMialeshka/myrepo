package it.mialeshka.mapper;

import it.mialeshka.dto.UserShopDto;
import it.mialeshka.entity.UserShop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserShopMapper {
    @Mappings({
            @Mapping(target = "password", constant = ""),
    })
    public UserShopDto toUserShopDto(UserShop userShop);
    public UserShop toUserShop(UserShopDto userShopDto);
}
