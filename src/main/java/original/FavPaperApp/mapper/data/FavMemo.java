package original.FavPaperApp.mapper.data;

import jakarta.websocket.Encoder.Text;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class FavMemo {
    private int userId;
    private int paperId;
    private int fav;
    private String memo;
    private LocalDateTime registeredAt;

}
