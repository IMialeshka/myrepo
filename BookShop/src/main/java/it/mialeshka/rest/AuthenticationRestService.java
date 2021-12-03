package it.mialeshka.rest;

import it.mialeshka.util.AuthResponse;
import it.mialeshka.util.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;



@RestController
public class AuthenticationRestService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtTokenUtil;


    @PostMapping(value ="/login")
    @ResponseBody
    public ResponseEntity<?> login(String username, String password)  {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        final String jwt = jwtTokenUtil.generateToken(username);

        return ResponseEntity.ok(new AuthResponse(jwt));

    }

}
