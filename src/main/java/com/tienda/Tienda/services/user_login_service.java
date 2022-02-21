package com.tienda.Tienda.services;

import com.tienda.Tienda.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class user_login_service implements UserDetailsService {

    @Autowired
    private User_service repositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User usuario = repositorio.findByNombre(username);

        // UserDetails userDet = new User(usuario.getNombre(), usuario.getPassword());
        UserDetails userDet = null;
        return userDet;
    }

}
