package it.mialeshka.service;

import it.mialeshka.dto.UserShopDto;
import it.mialeshka.entity.UserShop;

public interface UserShopService {
    public void addUserShop(UserShopDto userShopDto);
    public void deleteUserShop(Long userShopId);
    public UserShopDto findByUserName(String login);
}
