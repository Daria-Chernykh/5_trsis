/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package com.example.Lab3.service;


import com.example.Lab3.model.UserPE;
import org.springframework.security.core.authority.AuthorityUtils;

public class MyLogin extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private UserPE user;

    public MyLogin(UserPE user) {
        super(user.getLogin(), user.getPassHash(), AuthorityUtils.createAuthorityList("ALL"));
        this.user = user;
    }

}
