package original.FavPaperApp.service;

import org.springframework.stereotype.Service;
import original.FavPaperApp.mapper.PaperMapper;
import original.FavPaperApp.mapper.PaperWithTagDTOMapper;
import original.FavPaperApp.mapper.data.Paper;
import original.FavPaperApp.mapper.data.PaperWithTagDTO;

import java.util.Collections;
import java.util.List;

@Service
public class PaperService {

    public final PaperMapper mapper;

    public PaperService(PaperMapper mapper, PaperWithTagDTOMapper paperWithTagDTOMapper)  {
        this.mapper = mapper;
    }

    //紙の一覧表示
    public List<Paper> showPaper() {
        return mapper.selectPaperList();
    }

    //紙名で検索して表示
    public Paper searchPaperName(String paperName) {
        return mapper.selectPaper(paperName);
    }

    //紙の登録
    public Paper registerPaper(Paper paper) throws Exception {
        Paper existsPaper = searchPaperName(paper.getPaperName());
        if (existsPaper != null){  //例外処理
            throw new DuplicateException(paper.getPaperName() + "はすでに登録されています");
        }
        mapper.insertPaper(paper);
        return mapper.selectPaper(paper.getPaperName());
    }

    //紙の上書き
    public Paper updatePaper(Paper paper) {
        mapper.updatePaper(paper);
        return mapper.selectPaper(paper.getPaperName());
    }

    //紙の削除
    public void deletePaper(int paperId) throws Exception {
        int rowsAffected = mapper.deletePaper(paperId);
        if (rowsAffected == 0) {  //削除されなかった場合
            throw new NotFoundException("paperId：" + paperId + "が見つかりません");
        }
    }
}
