package original.FavPaperApp.service;

import org.springframework.stereotype.Service;
import original.FavPaperApp.mapper.PaperViewDTOMapper;
import original.FavPaperApp.mapper.data.PaperViewDTO;

import java.util.List;

@Service
public class PaperViewDTOService {

    private final PaperViewDTOMapper mapper;

    public PaperViewDTOService(PaperViewDTOMapper paperViewDTOMapper) {
        this.mapper = paperViewDTOMapper;
    }

    public List<PaperViewDTO> getPaper() {
        return mapper.selectPaperListAll();
    }
}
