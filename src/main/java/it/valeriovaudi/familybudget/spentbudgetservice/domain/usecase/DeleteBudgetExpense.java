package it.valeriovaudi.familybudget.spentbudgetservice.domain.usecase;

import it.valeriovaudi.familybudget.spentbudgetservice.domain.model.budget.BudgetExpenseId;
import it.valeriovaudi.familybudget.spentbudgetservice.domain.model.budget.BudgetExpenseNotFoundException;
import it.valeriovaudi.familybudget.spentbudgetservice.domain.repository.AttachmentRepository;
import it.valeriovaudi.familybudget.spentbudgetservice.domain.repository.BudgetExpenseRepository;

public class DeleteBudgetExpense {

    private final BudgetExpenseRepository budgetExpenseRepository;
    private final AttachmentRepository attachmentRepository;

    public DeleteBudgetExpense(BudgetExpenseRepository budgetExpenseRepository, AttachmentRepository attachmentRepository) {

        this.budgetExpenseRepository = budgetExpenseRepository;
        this.attachmentRepository = attachmentRepository;
    }

    public void delete(BudgetExpenseId budgetExpenseId) {
        budgetExpenseRepository.findFor(budgetExpenseId)
                .ifPresentOrElse(budgetExpense -> {
                            budgetExpense.getAttachmentFileNames()
                                    .forEach(attachmentFileName -> attachmentRepository.delete(budgetExpense, attachmentFileName));
                            budgetExpenseRepository.delete(budgetExpense.getId());
                        },
                        () -> {
                            throw new BudgetExpenseNotFoundException();
                        });
    }
}
