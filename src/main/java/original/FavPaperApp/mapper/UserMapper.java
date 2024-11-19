package original.FavPaperApp.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import original.FavPaperApp.mapper.data.Paper;
import original.FavPaperApp.mapper.data.User;

import java.util.List;

@Mapper
public interface UserMapper {

    //ユーザーの一覧表示
    @Select("select * from user order by user_id asc")
    List<User> selectUserList();

    //e-mailで検索して表示
    @Select("select * from user where email = #{email} order by user_id asc")
    User selectUser(String email);

    //ユーザーの登録
    @Insert("insert user(user_name, email) values(#{userName}, #{email})")
    void insertUser(User user);

    @Delete("delete from user where user_id = #{userId}")
    int deleteUser(int userId); // 削除された行数を返す
;
}
