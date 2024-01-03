package com.optimagrowth.organization.events.source;

import com.optimagrowth.organization.events.model.OrganizationChangeModel;
import com.optimagrowth.organization.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.stereotype.Component;
import com.optimagrowth.organization.utils.UserContext;
import org.springframework.messaging.support.MessageBuilder;

@Component
public class SimpleSourceBean {

    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    public enum ActionEnum {
        GET,
        CREATED,
        UPDATED,
        DELETED
    }
    public SimpleSourceBean(Source source){
        this.source =source;
    }

    public void publishOrganizationChange(ActionEnum actionEnum, String organizationId){
        logger.debug("Sending kafka message {} for organization ID : {}",actionEnum, organizationId);
        OrganizationChangeModel change =  new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                actionEnum.toString(),
                organizationId,
                UserContextHolder.getContext().getCorrelationId());

        source.output().send(MessageBuilder
                .withPayload(change)
                .build());
    }
}
