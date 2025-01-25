package original.FavPaperApp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import original.FavPaperApp.mapper.data.FavMemo;
import original.FavPaperApp.mapper.data.FavMemoView;

import java.util.List;

@Mapper
public interface FavMemoViewMapper {

    //user_idで検索して紙情報とあわせて表示
    @Select("""
    select p.paper_name, pt.type_name, p.description, fm.fav, fm.memo, fm.registered_at
        from paper p
        inner join paper_type pt on p.type_id = pt.type_id
        inner join fav_memo fm on p.paper_id = fm.paper_id
        where fm.user_id = #{userId}
    """)
    List<FavMemoView> selectFavMemoByUser(int userId);
}
