package app.web.dto;

import app.model.Wish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishResponse {

    private int status;
    private boolean successful;
    private Wish wish;
    private String message;
}
