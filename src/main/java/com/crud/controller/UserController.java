package com.crud.controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.crud.dto.RequestRefreshTokenDto;
import com.crud.dto.ResponseTokenDto;
import com.crud.dto.RoleToUseDto;
import com.crud.dto.UserDto;
import com.crud.entity.Role;
import com.crud.entity.User;
import com.crud.service.UserServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api")
public class UserController {
    
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.getUsers(); 
    }

    @PostMapping("/users/save")
    public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
        User newUser = modelMapper.map(userDto, User.class);
        return ResponseEntity.ok().body(newUser);
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUseDto roleToUseDto) {
        userService.addUserToRole(roleToUseDto.getEmail(), roleToUseDto.getRoleName());
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RequestRefreshTokenDto refreshTokenDto
    ) {
        try {
            Integer expiresIn = 3600 * 1000;
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refreshTokenDto.getRefresh_token());
            String email = decodedJWT.getSubject();
            User user = userService.getUser(email);
            String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiresIn))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(algorithm);

            String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiresIn * 4))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
            ResponseTokenDto responseToken = new ResponseTokenDto(access_token, refresh_token, "Bearer", expiresIn / 1000);
            return ResponseEntity.ok(responseToken);
        } catch (Exception e) {
            Map<String, String> failed = new HashMap<>();
            failed.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(failed);
        }
    }
}
