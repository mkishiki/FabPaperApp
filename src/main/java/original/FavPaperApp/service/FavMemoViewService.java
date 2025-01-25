package original.FavPaperApp.service;

import org.springframework.stereotype.Service;
import original.FavPaperApp.mapper.FavMemoViewMapper;
import original.FavPaperApp.mapper.data.FavMemo;
import original.FavPaperApp.mapper.data.FavMemoView;

import java.util.List;

@Service
public class FavMemoViewService {

    public final FavMemoViewMapper mapper;

    public FavMemoViewService(FavMemoViewMapper mapper)  {
        this.mapper = mapper;
    }

    //user_idで検索して表示
    public List<FavMemoView> searchFavMemoByUser(int userId) {
        return mapper.selectFavMemoByUser(userId);
    }

}
