package com.swedbank.datareader.repository;

import com.swedbank.datareader.model.ResponseMetric;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponseMetricRepository extends ElasticsearchRepository<ResponseMetric, UUID> {
}
