package original.FavPaperApp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import original.FavPaperApp.mapper.data.Paper;
import original.FavPaperApp.mapper.data.PaperViewDTO;
import original.FavPaperApp.mapper.data.PaperWithTagDTO;

import java.util.List;

@Mapper
public interface PaperViewDTOMapper {

    @Select("""
        select p.paper_name, pt.type_name, p.description, GROUP_CONCAT(t.tag_name ORDER BY t.tag_id ASC) as tagNames,
        null as userFav
        from paper p
        inner join paper_type pt on p.type_id = pt.type_id
        inner join paper_tag ptg on p.paper_id = ptg.paper_id
        inner join tag t on ptg.tag_id = t.tag_id
        group by p.paper_id, p.paper_name, pt.type_name, p.description;
    """)
    List<PaperViewDTO> selectPaperListAll();
}
