package com.ristify.ristifybackend.service;

import com.ristify.ristifybackend.dto.DTOMapper;
import com.ristify.ristifybackend.dto.user.UserDTO;
import com.ristify.ristifybackend.models.User;
import com.ristify.ristifybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO: tests - Andrei
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(DTOMapper::mapUserToDTO).collect(Collectors.toList());
    }

    public Optional<UserDTO> findById(final Integer id) {
        return userRepository.findById(id).map(DTOMapper::mapUserToDTO);
    }

    public Optional<UserDTO> findByUsername(final String username) {
        return userRepository.findByUsername(username).map(DTOMapper::mapUserToDTO);
    }

    public Optional<UserDTO> saveUser(final User user) {
        return Optional.of(DTOMapper.mapUserToDTO(userRepository.save(user)));
    }

    public Optional<UserDTO> deleteUserById(final Integer id) {
        return userRepository.deleteUserById(id).map(DTOMapper::mapUserToDTO);
    }
}