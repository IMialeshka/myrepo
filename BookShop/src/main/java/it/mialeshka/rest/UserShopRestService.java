package it.mialeshka.rest;

import it.mialeshka.dto.UserShopDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.mialeshka.entity.UserShop;
import it.mialeshka.util.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.servlet.AuthorizeRequestsDsl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import it.mialeshka.service.UserShopService;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@RestController
public class UserShopRestService {
    @Autowired
    private UserShopService userShopService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;



    @PostMapping(value = "/users", consumes = "application/json")
    @ApiOperation("new user")
    public ResponseEntity<UserShopDto> addNewUser (@RequestBody UserShopDto userShopDto){
        userShopService.addUserShop(userShopDto);
        return new ResponseEntity<>(userShopDto, HttpStatus.CREATED);
    }
    @DeleteMapping("/users/{id}")
    @ApiOperation("dell user")
    public ResponseEntity dellUserShop(@PathVariable Long id){
        userShopService.deleteUserShop(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
