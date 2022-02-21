package com.tienda.Tienda.services;

import java.util.ArrayList;
import java.util.List;

import com.tienda.Tienda.models.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class user_login_service implements UserDetailsService {

    @Autowired
    private User_service repositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users usuario = repositorio.findBynombre(username);

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));

        UserDetails userDet = new User(usuario.getNombre(), usuario.getPassword(), roles);

        return userDet;
    }

}
