package original.FavPaperApp.mapper;

import org.apache.ibatis.annotations.*;
import original.FavPaperApp.mapper.data.Paper;

import java.util.List;

@Mapper
public interface PaperMapper {

    //紙の一覧表示
    @Select("select * from paper order by paper_id asc")
    List<Paper> selectPaperList();

    //紙名で検索して表示
    @Select("select * from paper where paper_name = #{paperName} order by paper_id asc")
    Paper selectPaper(String paperName);

    //紙の登録
    @Insert("insert paper(paper_name, type_id) values(#{paperName}, #{typeId})")
    void insertPaper(Paper paper);

    //紙名が一致する列の、紙の種類を登録
    @Update("update paper set type_id = #{typeId} where paper_name = #{paperName}")
    void updatePaper(Paper paper);

    @Delete("delete from paper where paper_id = #{paperId}")
    int deletePaper(int paperId); // 削除された行数を返す
}