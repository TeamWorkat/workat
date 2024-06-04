package org.workat.workat_project.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDTO {
    private int user_id;
    private String user_email;
    private String user_pwd;
    private String user_nm;
    private String user_tel;
    private String role;
    private LocalDateTime created_date;
    private LocalDateTime modified_date;
    private String status;
    private int login_count;
    private String account_locked;
    private String oauth;
}