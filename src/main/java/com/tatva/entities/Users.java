package com.tatva.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @Column(name = "user_id")
    private int id;

    private String username;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    private String role;

    @Column(name = "create_dt")
    private String createDt;

    
}
