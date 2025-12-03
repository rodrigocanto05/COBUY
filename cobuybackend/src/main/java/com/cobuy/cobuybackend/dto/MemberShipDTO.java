package com.cobuy.cobuybackend.dto;

public class MemberShipDTO {

    private Integer id;
    private String role;
    private String userName;
    private String userEmail;

    public MemberShipDTO(Integer id, String role, String userName, String userEmail) {
        this.id = id;
        this.role = role;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public Integer getId() { return id; }
    public String getRole() { return role; }
    public String getUserName() { return userName; }
    public String getUserEmail() { return userEmail; }

    public void setId(Integer id) { this.id = id; }
    public void setRole(String role) { this.role = role; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
}
