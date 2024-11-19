package original.FavPaperApp.service;

import org.springframework.stereotype.Service;
import original.FavPaperApp.mapper.UserMapper;
import original.FavPaperApp.mapper.data.Paper;
import original.FavPaperApp.mapper.data.User;

import java.util.List;

@Service
public class UserService {

    public final UserMapper mapper;

    public UserService(UserMapper mapper)  {
        this.mapper = mapper;
    }

    //ユーザーの一覧表示
    public List<User> showUser() {
        return mapper.selectUserList();
    }

    //e-mailで検索して表示
    public User searchUser(String email) {
        return mapper.selectUser(email);
    }

    //ユーザーの登録
    public User registerUser(User user) throws Exception {
        User existsUser = searchUser(user.getEmail());
        if (existsUser != null){  //例外処理
            throw new DuplicateException("メールアドレス" + user.getEmail() + "はすでに登録されています");
        }
        mapper.insertUser(user);
        return mapper.selectUser(user.getEmail());
    }

    //ユーザーの削除
    public void deleteUser(int userId) throws Exception {
        int rowsAffected = mapper.deleteUser(userId);
        if (rowsAffected == 0) {  //削除されなかった場合
            throw new NotFoundException("userId：" + userId + "が見つかりません");
        }
    }


}
