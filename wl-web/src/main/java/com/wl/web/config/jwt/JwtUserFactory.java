package com.wl.web.config.jwt;

import com.wl.web.user.model.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {

        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getPhone(),
                user.getEmail(),
                user.getAvatar(),
                user.getLanguage(),
                user.getState(),
                user.getOperatorCode(),
                user.getCreateTime(),
                user.getUpdateTime(),
                user.getAccess(),
                user.getAuthorities()
        );
    }

}
