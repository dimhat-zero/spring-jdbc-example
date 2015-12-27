package org.dimhat.example.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * TODO
 * @author dimhat
 * @date 2015年12月27日 下午10:38:04
 * @version 1.0
 */
public class User implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 7093664696475241055L;

    private Long              id;

    private String            username;

    private String            password;

    private Boolean           loacked          = Boolean.FALSE;

    private Timestamp         lastLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoacked() {
        return loacked;
    }

    public void setLoacked(Boolean loacked) {
        this.loacked = loacked;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id).append(", username=").append(username).append(", password=")
            .append(password).append(", loacked=").append(loacked).append(", lastLogin=").append(lastLogin).append("]");
        return builder.toString();
    }

}
