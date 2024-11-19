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

    // タグIDで紙とタグの情報を取得
    @GetMapping("/paper/with_tag")
    public ResponseEntity<List<PaperWithTagDTO>> getPapersByTagId(@RequestParam int tagId) {
        List<PaperWithTagDTO> papersWithTag = service.getPapersByTagId(tagId);
        return ResponseEntity.ok(papersWithTag);
    }
}
