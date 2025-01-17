package original.FavPaperApp.mapper;

import org.apache.ibatis.annotations.*;
import original.FavPaperApp.mapper.data.Paper;

import java.util.List;

@Mapper
public interface PaperMapper {

    //紙の一覧表示
    @Select("select * from paper order by paper_id asc")
    List<Paper> selectPaperList();

//紙の一覧表示…paper_tag、tagもinner joinする場合。Paperクラスにフィールドが必要。
//    @Select("""
//        select p.paper_id, p.paper_name, p.type_id, p.description, pt.tag_id, t.tag_name
//        from paper p
//        inner join paper_tag pt on p.paper_id = pt.paper_id
//        inner join tag t on pt.tag_id = t.tag_id
//        order by paper_id asc
//    """)
//    List<Paper> selectPaperList();

    //紙名で検索して表示
    @Select("select * from paper where paper_name = #{paperName} order by paper_id asc")
    Paper selectPaper(String paperName);

    //紙の登録
    @Insert("insert paper(paper_name, type_id) values(#{paperName}, #{typeId})")
    void insertPaper(Paper paper);

    //type_nameが一致する列にtype_idを上書き（紙の種類を上書き）
    @Update("update paper set type_id = #{typeId} where paper_name = #{paperName}")
    void updatePaper(Paper paper);

    //紙の削除
    @Delete("delete from paper where paper_id = #{paperId}")
    int deletePaper(int paperId); // 削除された行数を返す
}
