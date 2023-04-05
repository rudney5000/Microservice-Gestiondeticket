package com.dedytech.ticketservice.repository.elastic;

import com.dedytech.ticketservice.entity.elastic.TicketModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TicketElasticRepository extends ElasticsearchRepository<TicketModel, String> {
}
