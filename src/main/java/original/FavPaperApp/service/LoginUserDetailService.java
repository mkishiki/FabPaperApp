package original.FavPaperApp.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LoginUserDetailService implements UserDetailsService {

    private final UserService userService;

    public LoginUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // UserServiceを使ってメールアドレスでユーザーを検索
        original.FavPaperApp.mapper.data.User appUser = userService.searchUser(email);

        if (appUser == null) {
            throw new UsernameNotFoundException("メールアドレス: " + email + " で登録されているユーザーが見つかりません");
        }

        // rolesフィールドから権限を取得
        String role = "ROLE_" + appUser.getRoles().toUpperCase(); // ROLE_プレフィックスを追加

        // Spring SecurityのUserDetailsを構築
        return new User(
                appUser.getEmail(),
                appUser.getPassword(), // データベースから取得したエンコード済みパスワードをそのまま使用
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );

    }
}
