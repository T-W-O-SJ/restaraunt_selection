package com.github.twosj.selection.web.user;

import com.github.twosj.selection.model.Role;
import com.github.twosj.selection.model.User;
import com.github.twosj.selection.util.JsonUtil;
import com.github.twosj.selection.web.MatcherFactory;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.github.twosj.selection.web.vote.VoteTestData.votesOfAdmin;
import static com.github.twosj.selection.web.vote.VoteTestData.votesOfUser;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "votes", "registered", "password");
    public static MatcherFactory.Matcher<User> USER_WITH_VOTES_MATCHER =
            MatcherFactory.usingAssertions(User.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("registered", "votes.user", "password").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final int NOT_FOUND = 100;
    public static final String USER_MAIL = "user@yandex.ru";
    public static final String ADMIN_MAIL = "admin@gmail.com";

    public static final User user = new User(USER_ID, "User", USER_MAIL, "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", ADMIN_MAIL, "admin", Role.ADMIN, Role.USER);
    public static final User user2 = new User(USER_ID + 2, "User2", "user2@yandex.ru", "password2", Role.USER);
    public static final User user3 = new User(USER_ID + 3, "User3", "user3@yandex.ru", "password3", Role.USER);

    static {
        user.setVotes(votesOfUser);
        admin.setVotes(votesOfAdmin);
    }

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(USER_ID, "UpdatedName", USER_MAIL, "newPass", false, new Date(), List.of(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String password) {
        return JsonUtil.writeAdditionProps(user, "password", password);
    }
}
