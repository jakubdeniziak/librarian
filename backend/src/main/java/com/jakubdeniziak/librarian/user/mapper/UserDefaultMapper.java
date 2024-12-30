package com.jakubdeniziak.librarian.user.mapper;

import com.jakubdeniziak.librarian.user.domain.User;
import com.jakubdeniziak.librarian.user.dto.UserRequest;
import com.jakubdeniziak.librarian.user.dto.UserResponse;
import com.jakubdeniziak.librarian.user.dto.UsersResponse;
import com.jakubdeniziak.librarian.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserDefaultMapper implements UserMapper {

    @Override
    public User map(UUID id, UserRequest request) {
        return User.builder()
                .id(id)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .nickname(request.getNickname())
                .build();
    }

    @Override
    public UserEntity map(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .nickname(user.getNickname())
                .build();
    }

    @Override
    public User map(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .nickname(user.getNickname())
                .build();
    }

    @Override
    public List<User> map(List<UserEntity> users) {
        return users.stream()
                .map(this::map)
                .toList();
    }

    @Override
    public UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .nickname(user.getNickname())
                .build();
    }

    @Override
    public UsersResponse mapToResponse(List<User> users) {
        List<UsersResponse.User> responseUsers = users.stream()
                .map(user -> UsersResponse.User.builder()
                        .id(user.getId())
                        .nickname(user.getNickname())
                        .build())
                .toList();
        return UsersResponse.builder()
                .users(responseUsers)
                .build();
    }

}
