package az.ticket.services;

/**
 *
 * @author Azad M
 */
public interface UserService {

    public void login() throws Exception;

    public void logout() throws Exception;

    public void blockUser() throws Exception;

    public void unblockUser() throws Exception;

    public void registerUser() throws Exception;
}
