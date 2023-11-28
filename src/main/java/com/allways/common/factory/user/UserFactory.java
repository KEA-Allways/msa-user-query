package com.allways.common.factory.user;

import com.allways.domain.user.entity.User;

public class UserFactory {
    public static User createUser(){
        return new User("userIdqwer","12345620a!",
                "nicknameqwer", "emailqwer@email.com", "profileImgSeqqwer");
    }

    public static User createUser(String userId, String password,
                                  String nickname, String email, String profileImgSeq) {
        return new User(userId, password, nickname, email, profileImgSeq);
    }
}
