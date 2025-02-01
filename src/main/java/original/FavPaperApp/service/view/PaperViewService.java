package original.FavPaperApp.service.view;

import org.springframework.stereotype.Service;
import original.FavPaperApp.mapper.PaperViewMapper;
import original.FavPaperApp.mapper.data.PaperTagView;
import original.FavPaperApp.mapper.data.PaperView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaperViewService {

    private final PaperViewMapper mapper;

    public PaperViewService(PaperViewMapper paperViewMapper) {
        this.mapper = paperViewMapper;
    }

    // 紙の一覧を表示
    public List<PaperView> showPaperAll() {
        return fetchPaperViewsWithTags(); // 全データ取得
    }

    // 紙を検索して一覧を表示（紙名・種類・タグを条件としてフィルタリング）
    public List<PaperView> searchPapers(String paperName, String typeName, String tagName) {
        // 紙のデータをすべて取得
        List<PaperView> paperList = fetchPaperViewsWithTags();

        // デバッグ用: 全データを出力
        System.out.println("全紙データ: " + paperList);

        // フィルタリング処理
        List<PaperView> filteredPapers = paperList.stream()
                .filter(p -> (paperName == null || p.getPaperName().trim().toLowerCase().contains(paperName.trim().toLowerCase()))) // 大文字小文字を無視
                .filter(p -> (typeName == null || p.getTypeName().trim().toLowerCase().contains(typeName.trim().toLowerCase())))   // 同じく種類のフィルタリング
                .filter(p -> (tagName == null || p.getTagNamesList().stream().anyMatch(tag -> tag.trim().toLowerCase().contains(tagName.trim().toLowerCase())))) // タグ名でフィルタ
                .collect(Collectors.toList());

        // デバッグ用: フィルタリング後のデータを出力
        System.out.println("フィルタリング後のデータ: " + filteredPapers);

        return filteredPapers;
    }

    // 紙のデータを取得し、タグ情報を付与する共通メソッド
    private List<PaperView> fetchPaperViewsWithTags() {
        // 紙の一覧を取得
        List<PaperView> paperList = mapper.selectPaperListAll();

        // タグ情報を取得
        List<PaperTagView> paperTags = mapper.selectTagsForPapers();

        // タグ情報を紙IDでグループ化
        Map<Integer, List<String>> tagMap = paperTags.stream()
                .collect(Collectors.groupingBy(
                        PaperTagView::getPaperId,                          // グループ化キー: paperId
                        Collectors.mapping(PaperTagView::getTagName,      // タグ名リストを値としてグループ化
                                Collectors.toList())
                ));

        // 各紙にタグリストを設定
        for (PaperView paper : paperList) {
            paper.setTagNamesList(tagMap.getOrDefault(paper.getPaperId(), List.of())); // タグがない場合は空リスト
        }

        return paperList;
    }
}


//package original.FavPaperApp.service;
//
//import org.springframework.stereotype.Service;
//import original.FavPaperApp.mapper.PaperViewMapper;
//import original.FavPaperApp.mapper.data.PaperView;
//
//import java.util.List;
//
//@Service
//public class PaperViewService {
//
//    private final PaperViewMapper mapper;
//
//    public PaperViewService(PaperViewMapper paperViewDTOMapper) {
//        this.mapper = paperViewDTOMapper;
//    }
//
//    //紙の一覧（用途、タグ含む）を表示
//    public List<PaperView> showPaperAll() {
//        return mapper.selectPaperListAll();
//    }
//}
