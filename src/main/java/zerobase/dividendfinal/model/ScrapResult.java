package zerobase.dividendfinal.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScrapResult {

    private Company company;

    private List<Dividend> dividends;

    public ScrapResult(){
        this.dividends = new ArrayList<>();
    }
}
