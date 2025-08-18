package ru.praktikum.stellarburgers.api;

public class UserDto {
    public String email;
    public String password;
    public String name;
    public UserDto() {}
    public UserDto(String email, String password, String name) {
        this.email = email; this.password = password; this.name = name;
    }
}
