package it.mialeshka.service.impl;

import it.mialeshka.dto.UserShopDto;
import it.mialeshka.entity.UserShop;
import it.mialeshka.mapper.UserShopMapper;
import it.mialeshka.service.UserShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import it.mialeshka.repository.UserShopRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserShopServiceImpl implements UserShopService, UserDetailsService {
    private UserShopRepository userShopRepository;
    private UserShopMapper userShopMapper;

    @Autowired
    public void setUserShopRepository(UserShopRepository userShopRepository) {
        this.userShopRepository = userShopRepository;
    }

    @Autowired
    public void setUserShopMapper(UserShopMapper userShopMapper) {
        this.userShopMapper = userShopMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserShop user = userShopRepository.findUserByUserName(s);

        if(user == null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


    @Override
    public void addUserShop(UserShopDto userShopDto) {
        userShopRepository.saveAndFlush(userShopMapper.toUserShop(userShopDto));
    }

    @Override
    public void deleteUserShop(Long userShopId) {
        userShopRepository.deleteById(userShopId);
    }

    @Override
    public UserShopDto findByUserName(String login) {
        return  userShopMapper.toUserShopDto(userShopRepository.findUserByUserName(login));
    }
}
