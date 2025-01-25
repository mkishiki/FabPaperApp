package original.FavPaperApp.mapper.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaperTagView {
    private int paperId; // 紙のID
    private String tagName; // タグの名前
}
