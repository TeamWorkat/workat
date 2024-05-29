package org.workat.workat_project.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserListDTO {
    private int user_id;
    private String user_email;
    private String user_nm;
    private LocalDateTime created_date;
    private String status;
    private String account_locked;
}