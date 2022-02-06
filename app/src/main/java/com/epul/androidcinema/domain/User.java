package com.epul.androidcinema.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "email",
        "password",
        "username"
})

public class User implements Serializable {
    @JsonProperty("id")
        private Integer id;
    @JsonProperty("email")
        private String email;
    @JsonProperty("password")
        private String password;
    @JsonProperty("username")
        private String username;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }
    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }
    @JsonProperty("username")
    public void setRole(String username) {
        this.username = username;
    }

}
