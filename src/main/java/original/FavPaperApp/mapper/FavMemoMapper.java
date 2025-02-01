package original.FavPaperApp.mapper;

import org.apache.ibatis.annotations.*;
import original.FavPaperApp.mapper.data.FavMemo;

import java.util.List;

@Mapper
public interface FavMemoMapper {

    //ファブメモの一覧表示
    @Select("select * from fav_memo order by user_id asc, paper_id asc")
    List<FavMemo> selectFavMemoList();

    //user_id,paper_idで検索して表示
    @Select("select * from fav_memo where user_id = #{userId} and paper_id = #{paperId} order by user_id asc, paper_id asc")
    FavMemo selectFavMemo(int userId, int paperId);

    //userIdで検索してpaperIdの一覧を取得
    @Select("SELECT paper_id FROM fav_memo WHERE user_id = #{userId}")
    List<Integer> selectUserFavoritePaperIds(int userId);


    //ファブメモの登録
    @Insert("insert fav_memo(user_id, paper_id, fav, memo, registered_at) values(#{userId}, #{paperId}, #{fav}, #{memo}, #{registeredAt})")
    void insertFavMemo(FavMemo favMemo);

    //user_id,paper_idで検索して上書き（ファブ、メモ、現在時刻を上書き）
    @Update("update fav_memo set fav = #{fav} memo = #{memo} registered_at = #{registeredAt} where user_id = #{userId} and paper_id = #{paperId}")
    void updateFavMemo(FavMemo favMemo);

    //ファブメモの削除
    @Delete("delete from fav_memo where user_id = #{userId} and paper_id = #{paperId}")
    int deleteFavMemo(int userId, int paperId); // 削除された行数を返す
}
