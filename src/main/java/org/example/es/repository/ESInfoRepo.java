package org.example.es.repository;

import org.example.es.model.ESInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ESInfoRepo extends ElasticsearchRepository<ESInfo,String> {

}
