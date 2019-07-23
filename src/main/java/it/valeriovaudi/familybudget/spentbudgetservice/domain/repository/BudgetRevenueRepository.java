package it.valeriovaudi.familybudget.spentbudgetservice.domain.repository;

import it.valeriovaudi.familybudget.spentbudgetservice.domain.model.budget.BudgetRevenue;
import it.valeriovaudi.familybudget.spentbudgetservice.domain.model.time.Date;

import java.util.List;

public interface BudgetRevenueRepository {

    List<BudgetRevenue> findByDateRange(String user, Date star, Date end);

    void save(BudgetRevenue budgetRevenue);

    void update(BudgetRevenue budgetRevenue);

    void delete(String idBudgetRevenue);
}
