package zerobase.dividendfinal.scraper;


import zerobase.dividendfinal.model.Company;
import zerobase.dividendfinal.model.ScrapResult;

public interface Scraper {
    Company scrapCompanyByTicker(String ticker);
    ScrapResult scrap(Company company);

}
