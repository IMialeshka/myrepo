package it.mialeshka.rest;

import it.mialeshka.dto.UserShopDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.mialeshka.service.UserShopService;

@RestController
public class UserShopRestController {
    @Autowired
    private UserShopService userShopService;


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
