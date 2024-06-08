package org.workat.workat_project.user.entity;

import lombok.*;

@Getter
@Setter
public class LoginDTO {
    private String user_email;
    private String user_pwd;
}