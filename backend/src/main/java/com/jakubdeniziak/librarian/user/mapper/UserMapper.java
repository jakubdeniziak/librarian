package com.jakubdeniziak.librarian.user.mapper;

import com.jakubdeniziak.librarian.user.domain.User;
import com.jakubdeniziak.librarian.user.dto.UserRequest;
import com.jakubdeniziak.librarian.user.dto.UserResponse;
import com.jakubdeniziak.librarian.user.dto.UsersResponse;
import com.jakubdeniziak.librarian.user.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserMapper {

    User map(UUID id, UserRequest request);
    UserEntity map(User user);
    User map(UserEntity user);
    List<User> map(List<UserEntity> users);
    UserResponse mapToResponse(User user);
    UsersResponse mapToResponse(List<User> users);

}
