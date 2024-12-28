package original.FavPaperApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import original.FavPaperApp.mapper.data.PaperWithTagDTO;
import original.FavPaperApp.service.PaperWithTagDTOService;

import java.util.List;

@RestController
public class PaperWithTagDTOController {

    private final PaperWithTagDTOService service;

    public PaperWithTagDTOController(PaperWithTagDTOService service) {
        this.service = service;
    }

    // タグIDで紙とタグ名を取得
    @GetMapping("/paper/with_tag")
        public ResponseEntity<List<PaperWithTagDTO>> searchPaperListWithTag(@RequestParam int tagId) {
        List<PaperWithTagDTO> paperListWithTag = service.searchPaperListWithTag(tagId);
        return ResponseEntity.ok(paperListWithTag);
    }
}
