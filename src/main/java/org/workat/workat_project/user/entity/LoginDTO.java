package org.workat.workat_project.user.entity;

import lombok.*;

@Getter
@Setter
public class LoginDTO {
    private int user_id;
    private String user_email;
    private String user_pwd;
    private String role;
    private String status;
    private int login_count;
    private String account_locked;
    private String oauth;
}