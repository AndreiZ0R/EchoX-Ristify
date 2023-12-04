package com.ristify.ristifybackend.service.user;

import com.ristify.ristifybackend.dto.DTOMapper;
import com.ristify.ristifybackend.dto.friendship.FriendshipDTO;
import com.ristify.ristifybackend.dto.friendship.CreateFriendshipRequest;
import com.ristify.ristifybackend.models.user.User;
import com.ristify.ristifybackend.repository.user.FriendshipRepository;
import com.ristify.ristifybackend.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    public List<FriendshipDTO> getAllFriendships() {
        return friendshipRepository.findAll().stream().map(DTOMapper::mapFriendshipToDTO).collect(Collectors.toList());
    }

    public List<FriendshipDTO> getAllFriendsForId(final Integer id) {
        return friendshipRepository.getAllFriendsForId(id).stream().map(DTOMapper::mapFriendshipToDTO).collect(Collectors.toList());
    }

    public Optional<FriendshipDTO> findFriendship(final Integer id1, final Integer id2) {
        return friendshipRepository.findFriendship(id1, id2).map(DTOMapper::mapFriendshipToDTO);
    }

    public Optional<FriendshipDTO> saveFriendship(final CreateFriendshipRequest createFriendshipRequest) {
        List<User> foundUsers = userRepository.findMultipleById(List.of(createFriendshipRequest.userId1(), createFriendshipRequest.userId2()));

        return foundUsers.size() == 2 ?
               friendshipRepository.storeFriendship(createFriendshipRequest.userId1(), createFriendshipRequest.userId2()).map(DTOMapper::mapFriendshipToDTO) :
               Optional.empty();
    }

    public Optional<FriendshipDTO> deleteFriendship(final Integer id1, final Integer id2) {
        return friendshipRepository.deleteFriendship(id1, id2).map(DTOMapper::mapFriendshipToDTO);
    }
}