package original.FavPaperApp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import original.FavPaperApp.mapper.data.Paper;
import original.FavPaperApp.mapper.data.PaperWithTagDTO;

import java.util.List;

@Mapper
public interface PaperWithTagDTOMapper {

    //tag_idで検索、paper_tagテーブルからpaper_idを取得して紙とタグの一覧表示
    @Select("""
        select p.paper_id, p.paper_name, p.type_id, pt.tag_id, t.tag_name
        from paper p
        inner join paper_tag pt on p.paper_id = pt.paper_id
        inner join tag t on pt.tag_id = t.tag_id
        where pt.tag_id = #{tagId}
    """)
    List<PaperWithTagDTO> findPapersByTagId(@Param("tagId") int tagId);

}
