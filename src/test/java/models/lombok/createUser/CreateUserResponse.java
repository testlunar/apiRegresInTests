package models.lombok.createUser;

import lombok.Data;

@Data
public class CreateUserResponse {
    public String name, job, id, createdAt;
}
