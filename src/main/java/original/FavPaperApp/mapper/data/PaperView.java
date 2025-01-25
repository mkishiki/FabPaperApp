package original.FavPaperApp.mapper.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PaperView {
    private int paperId;
    private String paperName;
    private String description;
    private String typeName;
    private List<String> tagNamesList; // タグ名のリスト
}
