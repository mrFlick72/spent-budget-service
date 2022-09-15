package it.valeriovaudi.onlyoneportal.budgetservice.budget.expense.action;

import it.valeriovaudi.onlyoneportal.budgetservice.budget.expense.model.BudgetExpense;
import it.valeriovaudi.onlyoneportal.budgetservice.budget.expense.repository.BudgetExpenseRepository;
import it.valeriovaudi.onlyoneportal.budgetservice.domain.model.IdProvider;
import it.valeriovaudi.onlyoneportal.budgetservice.searchtag.SearchTag;
import it.valeriovaudi.onlyoneportal.budgetservice.user.UserRepository;

import java.util.Optional;

public class CreateBudgetExpense {

    private final UserRepository userRepository;
    private final BudgetExpenseRepository budgetExpenseRepository;
    private final IdProvider idProvider;
    private final String DEFAULT_TAG = SearchTag.DEFAULT_KEY;

    public CreateBudgetExpense(BudgetExpenseRepository budgetExpenseRepository,
                               UserRepository userRepository, IdProvider idProvider) {
        this.budgetExpenseRepository = budgetExpenseRepository;
        this.userRepository = userRepository;
        this.idProvider = idProvider;
    }

    public BudgetExpense newBudgetExpense(NewBudgetExpenseRequest newBudgetExpenseRequest) {
        BudgetExpense budgetExpense = new BudgetExpense(idProvider.newBudgetExpenseId(),
                userRepository.currentLoggedUserName(),
                newBudgetExpenseRequest.getDate(),
                newBudgetExpenseRequest.getAmount(),
                newBudgetExpenseRequest.getNote(),
                getSearchTag(newBudgetExpenseRequest));

        budgetExpenseRepository.save(budgetExpense);
        return budgetExpense;
    }

    private String getSearchTag(NewBudgetExpenseRequest newBudgetExpenseRequest) {
        return Optional.ofNullable(newBudgetExpenseRequest.getTag())
                .map(tag -> tag.isBlank() ? DEFAULT_TAG : tag)
                .orElse(DEFAULT_TAG);
    }
}