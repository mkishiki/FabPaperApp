package original.FavPaperApp.service;

import org.springframework.stereotype.Service;
import original.FavPaperApp.mapper.FavMemoMapper;
import original.FavPaperApp.mapper.PaperMapper;
import original.FavPaperApp.mapper.PaperWithTagDTOMapper;
import original.FavPaperApp.mapper.data.FavMemo;
import original.FavPaperApp.mapper.data.Paper;

import java.util.List;

@Service
public class FavMemoService {

    public final FavMemoMapper mapper;

    public FavMemoService(FavMemoMapper mapper)  {
        this.mapper = mapper;
    }

    //ファブメモの一覧表示
    public List<FavMemo> showFavMemo() {
        return mapper.selectFavMemoList();
    }

    //user_id,paper_idで検索して表示
    public FavMemo searchFavMemo(int userId, int paperId) {
        return mapper.selectFavMemo(userId, paperId);
    }

    //ファブメモの登録
    public FavMemo registerFavMemo(FavMemo favMemo) throws Exception {
        FavMemo existsFavMemo = searchFavMemo(favMemo.getUserId(), favMemo.getPaperId());
        if (existsFavMemo != null){  //例外処理
            throw new DuplicateException("userId:" + favMemo.getUserId() + ", paperId:" + favMemo.getPaperId() + "のファブメモはすでに登録されています");
        }
        mapper.insertFavMemo(favMemo);
        return mapper.selectFavMemo(favMemo.getUserId(), favMemo.getPaperId());
    }

    //ファブメモの上書き
    public FavMemo updateFavMemo(FavMemo favMemo) {
        mapper.updateFavMemo(favMemo);
        return mapper.selectFavMemo(favMemo.getUserId(), favMemo.getPaperId());
    }

    //ファブメモの削除
    public void deleteFavMemo(int userId, int paperId) throws Exception {
        int rowsAffected = mapper.deleteFavMemo(userId, paperId);
        if (rowsAffected == 0) {  //削除されなかった場合
            throw new NotFoundException("userId: " + userId + ", paperId: " + paperId + "のファブメモが見つかりません");
        }
    }
}
