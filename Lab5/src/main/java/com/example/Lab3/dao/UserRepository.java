/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package com.example.Lab3.dao;

import com.example.Lab3.model.UserPE;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
        extends CrudRepository<UserPE, Integer> {

    UserPE findByLogin(String login);

}
