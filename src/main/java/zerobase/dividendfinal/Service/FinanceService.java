package zerobase.dividendfinal.Service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.dividendfinal.model.Company;
import zerobase.dividendfinal.model.Dividend;
import zerobase.dividendfinal.model.ScrapResult;
import zerobase.dividendfinal.persist.CompanyRepository;
import zerobase.dividendfinal.persist.DividendRepository;
import zerobase.dividendfinal.persist.entity.CompanyEntity;
import zerobase.dividendfinal.persist.entity.DividendEntity;

@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    public ScrapResult getDividendByCompanyName(String companyName) {

        // 1. 회사명을 기준으로 회사정보를 조회
        CompanyEntity company = this.companyRepository.findByName(companyName)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회사명입니다."));

        // 2.조회된 회사 ID 로 배당금 정보 조회
        List<DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(company.getId());

        // 3. 결과 조합 후 반환
//        List<Dividend> dividends = new ArrayList<>();
//        for (var entity : dividendEntities){
//            dividends.add(Dividend.builder()
//                                  .date(entity.getDate())
//                                  .dividend(entity.getDividend())
//                                  .build());
//        }

        List<Dividend> dividends = dividendEntities.stream()
                .map(e -> Dividend.builder()
                        .date(e.getDate())
                        .dividend(e.getDividend())
                        .build())
                .collect(Collectors.toList());

        return new ScrapResult(Company.builder()
                .ticker(company.getTicker())
                .name(company.getName())
                .build(),
                dividends);
    }
}
