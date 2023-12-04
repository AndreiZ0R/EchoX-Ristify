package com.ristify.ristifybackend.auth.message;

import com.ristify.ristifybackend.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private UserDTO user;
    private String token;
}
