package it.valeriovaudi.familybudget.budgetservice.web.config;

import it.valeriovaudi.familybudget.budgetservice.web.converter.StringToBudgetSearchCriteriaRepresentationConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToBudgetSearchCriteriaRepresentationConverter());
    }
}