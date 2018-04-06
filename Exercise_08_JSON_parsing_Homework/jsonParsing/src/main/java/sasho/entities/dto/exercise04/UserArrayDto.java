package sasho.entities.dto.exercise04;

import com.google.gson.annotations.Expose;
import sasho.entities.dto.exercise02.FullUserInfoDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserArrayDto implements Serializable
{
    @Expose
    private int usersCount;
    @Expose
    private List<FullUserInfoDto> users;

    public UserArrayDto()
    {
        this.users = new ArrayList<>();
    }

    public int getUsersCount()
    {
        return this.usersCount;
    }

    public void setUsersCount(int usersCount)
    {
        this.usersCount = usersCount;
    }

    public List<FullUserInfoDto> getUsers()
    {
        return this.users;
    }

    public void setUsers(List<FullUserInfoDto> users)
    {
        this.users = users;
    }
}
