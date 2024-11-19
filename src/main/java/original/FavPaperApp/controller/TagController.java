package original.FavPaperApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import original.FavPaperApp.mapper.data.Tag;
import original.FavPaperApp.service.TagService;

import java.util.List;

@RestController
public class TagController {

    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    //タグの一覧表示
    @GetMapping(value = "/tag/list")
    public List<Tag> tagList() {
        return service.showTag();
    }

    //タグの登録
    @PostMapping(value = "/tag")
    public ResponseEntity<Tag> registerTag(@RequestParam String tagName) throws Exception{
        Tag registerTag = service.registerTag(tagName);
        return new ResponseEntity<>(registerTag, HttpStatus.OK);
    }

    //タグの削除
    @DeleteMapping(value = "/tag/edit")
    public ResponseEntity<String> deleteTag(@RequestParam int tagId) throws Exception {
        service.deleteTag(tagId); // 削除処理を実行
        return new ResponseEntity<>("tagId：" + tagId + "を削除しました", HttpStatus.OK); // 成功した場合のみ "OK" を返す
    }

}
