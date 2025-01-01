package com.frederick.server.service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자 정보를 데이터베이스에서 조회
        Optional<com.frederick.server.document.User> loginUserOptional = userService.findByUsername(username);

        // 사용자가 존재하는지 확인
        if (loginUserOptional.isPresent()) {
            com.frederick.server.document.User user = loginUserOptional.get();

            // UserDetails 객체 생성 및 반환 (DB에서 가져온 username과 password 사용)
            return User.builder()
                    .username(user.getUsername()) // DB에서 가져온 username 사용
                    .password(user.getPassword()) // DB에서 가져온 암호화된 password 사용
                    .roles("USER") // 역할 설정
                    .build();
        }

        // 사용자가 없을 경우 예외 발생
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}