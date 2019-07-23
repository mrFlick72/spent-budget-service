package it.valeriovaudi.familybudget.spentbudgetservice.domain.repository;


import it.valeriovaudi.familybudget.spentbudgetservice.domain.model.SearchTag;

import java.util.List;

public interface SearchTagRepository {

    SearchTag findSearchTagBy(String key);
    List<SearchTag> findAllSearchTag();
    void save(SearchTag searchTag);
    void update(SearchTag searchTag);
    void delete(String key);
}
