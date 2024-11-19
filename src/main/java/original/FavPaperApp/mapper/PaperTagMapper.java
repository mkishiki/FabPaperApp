package original.FavPaperApp.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import original.FavPaperApp.mapper.data.Paper;
import original.FavPaperApp.mapper.data.PaperTag;
import original.FavPaperApp.mapper.data.Tag;

import java.util.List;

@Mapper
public interface PaperTagMapper {

    @Select("select * from paper_tag order by paper_id asc, tag_id asc")
    List<PaperTag> selectPaperTagList();

    //紙とタグの組み合わせを登録
    @Insert("insert paper_tag(paper_id, tag_id) values(#{paperId}, #{tagId})")
    int insertPaperTag(PaperTag paperTag);

    @Delete("delete from paper_tag where paper_id = #{paperId} and tag_id = #{tagId}" )
    int deletePaperTag(int paperId, int tagId);  // 削除された行数を返す
}
