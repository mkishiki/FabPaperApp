package original.FavPaperApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import original.FavPaperApp.mapper.data.PaperTag;
import original.FavPaperApp.service.DuplicateException;
import original.FavPaperApp.service.PaperTagService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class PaperTagController {

    private final PaperTagService service;

    public PaperTagController(PaperTagService service) {
        this.service = service;
    }

    //タグの一覧表示
    @GetMapping(value = "/paper_tag/list")
    public List<PaperTag> paperTagList() {
        return service.showPaperTag();
    }

    //ペーパータグの登録
    @PostMapping(value = "/paper_tag")
    public int registerPaperTag(@RequestBody PaperTag paperTag){
        return service.registerPaperTag(paperTag);
    }

    //ペーパータグの削除
    @DeleteMapping(value = "/paper_tag/edit")
    public ResponseEntity<String> deletePaperTag(@RequestParam int paperId, int tagId) throws Exception {
        service.deletePaperTag(paperId, tagId); // 削除処理を実行
        return new ResponseEntity<>("paperId：" + paperId + "tagId：" + tagId + "を削除しました", HttpStatus.OK); // 成功した場合のみ "OK" を返す
    }

    //例外処理
    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<Map<String, String>> handleDuplicatePaperTag(
            DuplicateException e, HttpServletRequest request) { //例外の情報e（今回はエラー文言）と、呼び出しもとからのリクエストを引数
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                "error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
