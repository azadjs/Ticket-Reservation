package az.ticket.beans;

import az.ticket.services.UserService;
import java.util.Date;

/**
 *
 * @author Azad M
 */
public class User implements UserService{
    private long userId;
    private String username;
    private String password;
    private String email;
    private String fullname;
    private Date registrationDate;
    private Date lastLoginDate;
    private boolean status;
    private int attempts;

    public User(long userId, String username, String password, String email, String fullname, Date registrationDate, Date lastLoginDate, boolean status, int attempts) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.registrationDate = registrationDate;
        this.lastLoginDate = lastLoginDate;
        this.status = status;
        this.attempts = attempts;
    }

    
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    @Override
    public void login() throws Exception {
        
    }

    @Override
    public void logout() throws Exception {
        
    }

    @Override
    public void blockUser() throws Exception {
        
    }

    @Override
    public void unblockUser() throws Exception {
        
    }

    @Override
    public void registerUser() throws Exception {
        
    }
    
}
