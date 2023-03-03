package com.tanchee.starcircleplus.user;

import com.tanchee.starcircleplus.user.User;
import org.springframework.data.repository.CrudRepository;

// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer>
{
}
