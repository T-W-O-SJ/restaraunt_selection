package com.git.selection.web.user;

import com.git.selection.model.User;
import org.springframework.stereotype.Controller;

import static com.git.selection.web.SecurityUtil.authUserId;


@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}