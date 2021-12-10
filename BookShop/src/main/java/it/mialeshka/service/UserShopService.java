package it.mialeshka.service;

import it.mialeshka.dto.UserShopDto;

public interface UserShopService {
    void addUserShop(UserShopDto userShopDto);
    void deleteUserShop(Long userShopId);
    UserShopDto findByUserName(String login);
}
