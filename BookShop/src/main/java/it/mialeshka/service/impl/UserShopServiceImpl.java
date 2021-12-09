package it.mialeshka.service.impl;

import it.mialeshka.dto.UserShopDto;
import it.mialeshka.entity.Role;
import it.mialeshka.entity.UserShop;
import it.mialeshka.mapper.UserShopMapper;
import it.mialeshka.repository.RoleRepository;
import it.mialeshka.service.UserShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import it.mialeshka.repository.UserShopRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserShopServiceImpl implements UserShopService, UserDetailsService {
    @Autowired
    private UserShopRepository userShopRepository;
    @Autowired
    private UserShopMapper userShopMapper;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserShop user = userShopRepository.findUserByUserName(s);
        user.setRoles(userShopRepository.findAllUserRole(user.getId()));


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
        UserShop user = userShopRepository.findUserByUserName(login);
        user.setRoles(userShopRepository.findAllUserRole(user.getId()));
        return  userShopMapper.toUserShopDto(user);
    }
}
