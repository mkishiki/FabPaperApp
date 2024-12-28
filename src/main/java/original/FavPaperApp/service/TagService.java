package original.FavPaperApp.service;

import org.springframework.stereotype.Service;
import original.FavPaperApp.mapper.TagMapper;
import original.FavPaperApp.mapper.data.Tag;

import java.util.List;

@Service
public class TagService {

    public final TagMapper mapper;

    public TagService(TagMapper mapper)  {
        this.mapper = mapper;
    }

    //タグの一覧表示
    public List<Tag> showTag() {
        return mapper.selectTagList();
    }

    //タグを検索
    public Tag searchTagName(String tagName) {
        return mapper.selectTag(tagName);
    }

    //タグの登録
    public Tag registerTag(String tagName){
        mapper.insertTag(tagName);
        return mapper.selectTag(tagName);
    }

    //タグの削除
    public void deleteTag(int tagId) throws Exception {
        int rowsAffected = mapper.deleteTag(tagId);
        if (rowsAffected == 0) {  //削除されなかった場合
            throw new NotFoundException("tagId: " + tagId + "が見つかりません: ");
        }
    }


}
