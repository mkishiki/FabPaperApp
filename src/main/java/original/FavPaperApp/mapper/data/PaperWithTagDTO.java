package original.FavPaperApp.mapper.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaperWithTagDTO {
    private int paperId;
    private String paperName;
    private int typeId;
    private int tagId;
    private String tagName;

}