package it.valeriovaudi.onlyoneportal.budgetservice.web.config;


import it.valeriovaudi.onlyoneportal.budgetservice.adapters.repository.*;
import it.valeriovaudi.onlyoneportal.budgetservice.domain.repository.BudgetExpenseRepository;
import it.valeriovaudi.onlyoneportal.budgetservice.domain.repository.BudgetRevenueRepository;
import it.valeriovaudi.onlyoneportal.budgetservice.domain.repository.SearchTagRepository;
import it.valeriovaudi.onlyoneportal.budgetservice.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public UserRepository userRepository() {
        return new SpringSecurityUserRepository();
    }

    @Bean
    public BudgetRevenueRepository jdbcBudgetRevenueRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcBudgetRevenueRepository(jdbcTemplate);
    }

    @Bean
    public BudgetExpenseRepository jdbBudgetExpenseRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcBudgetExpenseRepository(jdbcTemplate);
    }

    @Bean
    public SearchTagRepository jdbcSearchTagRepository(DynamoDbClient dynamoDbClient,
                                                       @Value("${budget-service.dynamo-db.search-tags.table-name}") String searchTagsDynamoDbTableName,
                                                       UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        JdbcSearchTagRepository jdbcSearchTagRepository = new JdbcSearchTagRepository(userRepository, jdbcTemplate);
        DynamoDBSearchTagRepository dynamoDBSearchTagRepository = new DynamoDBSearchTagRepository(searchTagsDynamoDbTableName, userRepository, dynamoDbClient, new DynamoDbAttributeValueFactory());
        return new CompositeSearchTagRepository(jdbcSearchTagRepository, dynamoDBSearchTagRepository);
    }

    @Bean
    public RestTemplate repositoryServiceRestTemplate() {
        return new RestTemplateBuilder().build();
    }
}
