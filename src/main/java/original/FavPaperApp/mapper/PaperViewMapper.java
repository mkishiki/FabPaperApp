package original.FavPaperApp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import original.FavPaperApp.mapper.data.PaperTag;
import original.FavPaperApp.mapper.data.PaperTagView;
import original.FavPaperApp.mapper.data.PaperView;

import java.util.List;

@Mapper
public interface PaperViewMapper {

    @Select("""
        select p.paper_id, p.paper_name, pt.type_name, p.description
        from paper p
        inner join paper_type pt on p.type_id = pt.type_id;
    """)
    List<PaperView> selectPaperListAll();

    @Select("""
        select ptg.paper_id, t.tag_name
        from paper_tag ptg
        inner join tag t on ptg.tag_id = t.tag_id;
    """)
    List<PaperTagView> selectTagsForPapers();

    // 名前で検索
    @Select("""
        select p.paper_id, p.paper_name, pt.type_name, p.description
        from paper p
        inner join paper_type pt on p.type_id = pt.type_id
        where p.paper_name like concat('%', #{paperName}, '%');
    """)
    List<PaperView> selectPapersByName(String paperName);
}

