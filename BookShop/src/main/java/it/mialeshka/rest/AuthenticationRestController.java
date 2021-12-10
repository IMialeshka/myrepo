package it.mialeshka.rest;

import it.mialeshka.service.UserShopService;
import it.mialeshka.util.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class AuthenticationRestController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtTokenUtil;

    @Autowired
    private UserShopService userShopService;


    @PostMapping(value ="/login")
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(String username, String password)  {
        Map<String, String> jwt = new HashMap<>();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        jwt.put("jwt", jwtTokenUtil.generateToken(username, userShopService.findByUserName(username).getRoles()));

        return ResponseEntity.ok(jwt);

    }

}
