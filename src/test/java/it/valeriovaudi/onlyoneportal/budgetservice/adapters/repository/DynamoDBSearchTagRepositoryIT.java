package it.valeriovaudi.onlyoneportal.budgetservice.adapters.repository;

import it.valeriovaudi.onlyoneportal.budgetservice.domain.repository.SearchTagRepository;
import it.valeriovaudi.onlyoneportal.budgetservice.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import static it.valeriovaudi.onlyoneportal.budgetservice.UserTestFixture.A_USER_NAME;
import static it.valeriovaudi.onlyoneportal.budgetservice.support.DatabaseUtils.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DynamoDBSearchTagRepositoryIT extends AbstractSearchTagRepositoryIT {


    private SearchTagRepository budgetExpenseRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        DynamoDbClient client = dynamoClient();
        resetDatabase();
        budgetExpenseRepository = new DynamoDBSearchTagRepository(SEARCH_TAG_TABLE_NAME, userRepository, client);

        given(userRepository.currentLoggedUserName())
                .willReturn(A_USER_NAME);
    }


    @Test
    public void findAll() {
        loadSearchTags().forEach(budgetExpenseRepository::save);
        super.findAll(budgetExpenseRepository);
    }
/*
    @Test
    public void findSearchTagBy() {
        super.findSearchTagBy(budgetExpenseRepository);
    }

    @Test
    public void delete() {
        super.delete(budgetExpenseRepository);
    }
*/

    @Test
    public void save() {
        super.save(budgetExpenseRepository);
    }
}