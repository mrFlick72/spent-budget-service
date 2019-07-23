package it.valeriovaudi.familybudget.spentbudgetservice.adapters.repository;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ToString
@ConfigurationProperties("attachment.repository")
public class AttachmentRepositoryConfigurationProperties {
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucketName;
    private String bucketPrefix;

}
