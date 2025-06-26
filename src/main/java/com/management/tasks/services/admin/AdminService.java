package com.management.tasks.services.admin;

import com.management.tasks.dto.UserDto;

import java.util.List;

public interface AdminService {

    List<UserDto> getUsers();
}
