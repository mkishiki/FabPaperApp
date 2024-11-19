package original.FavPaperApp.service;

import org.springframework.stereotype.Service;
import original.FavPaperApp.mapper.PaperTagMapper;
import original.FavPaperApp.mapper.data.PaperTag;

import java.util.List;

@Service
public class PaperTagService {

    public final PaperTagMapper mapper;

    public PaperTagService(PaperTagMapper mapper)  {
        this.mapper = mapper;
    }

    //ペーパータグの一覧表示
    public List<PaperTag> showPaperTag() {
        return mapper.selectPaperTagList();
    }

    //タグの登録
    public int registerPaperTag(PaperTag paperTag){
        return mapper.insertPaperTag(paperTag);
    }

    //タグの削除
    public void deletePaperTag(int paperId, int tagId) throws Exception {
        int rowsAffected = mapper.deletePaperTag(paperId, tagId);
        if (rowsAffected == 0) {  //削除されなかった場合
            throw new NotFoundException("paperId：" + paperId + "tagId：" + tagId + "の組み合わせは見つかりません");
        }
    }


}
