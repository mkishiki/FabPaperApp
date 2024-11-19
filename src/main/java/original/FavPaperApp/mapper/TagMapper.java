package original.FavPaperApp.mapper;

import org.apache.ibatis.annotations.*;
import original.FavPaperApp.mapper.data.Tag;

import java.util.List;

@Mapper
public interface TagMapper {

    @Select("select * from tag order by tag_id asc")
    List<Tag> selectTagList();

    @Select("select * from tag where tag_name = #{tagName} order by tag_id asc")
    Tag selectTag(String tagName);

    //タグを登録
    @Insert("insert tag(tag_name) values(#{tagName})")
    void insertTag(String tag);

    @Delete("delete from tag where tag_id = #{tagId}")
    int deleteTag(int tagId);  // 削除された行数を返す
}
