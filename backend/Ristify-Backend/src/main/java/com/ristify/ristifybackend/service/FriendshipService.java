package com.ristify.ristifybackend.service;

import com.ristify.ristifybackend.dto.DTOMapper;
import com.ristify.ristifybackend.dto.friendship.FriendshipDTO;
import com.ristify.ristifybackend.dto.friendship.SaveFriendshipDTO;
import com.ristify.ristifybackend.models.User;
import com.ristify.ristifybackend.repository.FriendshipRepository;
import com.ristify.ristifybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO: tests - Andrei
@Service
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendshipService(final FriendshipRepository friendshipRepository, final UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    public List<FriendshipDTO> getAllFriendships() {
        return friendshipRepository.findAll().stream().map(DTOMapper::mapFriendshipToDTO).collect(Collectors.toList());
    }

    public List<FriendshipDTO> getAllFriendsForId(final Integer id) {
        return friendshipRepository.getAllFriends(id).stream().map(DTOMapper::mapFriendshipToDTO).collect(Collectors.toList());
    }

    public Optional<FriendshipDTO> findFriendship(final Integer id1, final Integer id2) {
        return friendshipRepository.findFriendship(id1, id2).map(DTOMapper::mapFriendshipToDTO);
    }

    public Optional<FriendshipDTO> saveFriendship(final SaveFriendshipDTO saveFriendshipDTO) {
        List<User> foundUsers = userRepository.findMultipleById(List.of(saveFriendshipDTO.userId1(), saveFriendshipDTO.userId2()));

        return foundUsers.size() == 2 ?
               friendshipRepository.storeFriendship(saveFriendshipDTO.userId1(), saveFriendshipDTO.userId2()).map(DTOMapper::mapFriendshipToDTO) :
               Optional.empty();
    }

    public Optional<FriendshipDTO> deleteFriendship(final Integer id1, final Integer id2) {
        return friendshipRepository.deleteFriendship(id1, id2).map(DTOMapper::mapFriendshipToDTO);
    }
}
