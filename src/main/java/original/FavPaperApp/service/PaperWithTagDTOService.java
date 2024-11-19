package original.FavPaperApp.service;

import org.springframework.stereotype.Service;
import original.FavPaperApp.mapper.PaperWithTagDTOMapper;
import original.FavPaperApp.mapper.data.PaperWithTagDTO;

import java.util.List;

@Service
public class PaperWithTagDTOService {

    private final PaperWithTagDTOMapper paperWithTagDTOMapper;

    public PaperWithTagDTOService(PaperWithTagDTOMapper paperWithTagDTOMapper) {
        this.paperWithTagDTOMapper = paperWithTagDTOMapper;
    }

    // タグIDで紙とタグの情報を取得する
    public List<PaperWithTagDTO> getPapersByTagId(int tagId) {
        return paperWithTagDTOMapper.findPapersByTagId(tagId);
    }
}
