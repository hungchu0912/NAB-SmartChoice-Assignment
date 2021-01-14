package com.nab.dc.batch.product;

import com.nab.dc.batch.product.lazada.LazadaApiReader;
import com.nab.dc.batch.product.lazada.LazadaProductDetailProcessor;
import com.nab.dc.batch.product.shopee.ShopeeApiReader;
import com.nab.dc.batch.product.shopee.ShopeeProductDetailProcessor;
import com.nab.dc.batch.product.tiki.TikiApiReader;
import com.nab.dc.batch.product.tiki.TikiProductDetailProcessor;
import com.nab.dc.dto.lazada.LazadaProductDetailDto;
import com.nab.dc.dto.shopee.ShopeeProductDetailDto;
import com.nab.dc.dto.tiki.TikiProductDetailDto;
import com.nab.dc.entity.PublisherProductDetailEntity;
import com.nab.dc.entity.PublisherProductMappingEntity;
import com.nab.dc.fetcher.LazadaAPIFetcher;
import com.nab.dc.fetcher.ShopeeAPIFetcher;
import com.nab.dc.fetcher.TikiAPIFetcher;
import com.nab.dc.repository.PublisherProductDetailRepository;
import com.nab.dc.repository.PublisherProductMappingRepository;
import com.nab.dc.repository.PublisherRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class ProductBatchJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private PublisherProductDetailRepository publisherProductDetailRepository;

    @Autowired
    private PublisherProductMappingRepository publisherProductMappingRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Job collectTikiJob(Step step1Tiki) {
        return jobBuilderFactory.get("collectTiki")
                .incrementer(new RunIdIncrementer())
                .flow(step1Tiki)
                .end()
                .build();
    }

    @Bean
    public Step step1Tiki(ProductRESTAPIWriter writer) {
        return stepBuilderFactory.get("step1Tiki")
                .<Map<PublisherProductMappingEntity, TikiProductDetailDto>, List<PublisherProductDetailEntity>> chunk(10)
                .reader(new TikiApiReader(publisherProductMappingRepository, new TikiAPIFetcher()))
                .processor(new TikiProductDetailProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public Job collectShopeeJob(Step step1Shopee) {
        return jobBuilderFactory.get("collectShopee")
                .incrementer(new RunIdIncrementer())
                .flow(step1Shopee)
                .end()
                .build();
    }

    @Bean
    public Step step1Shopee(ProductRESTAPIWriter writer) {
        return stepBuilderFactory.get("step1Shopee")
                .<Map<PublisherProductMappingEntity, ShopeeProductDetailDto>, List<PublisherProductDetailEntity>> chunk(10)
                .reader(new ShopeeApiReader(publisherProductMappingRepository, new ShopeeAPIFetcher()))
                .processor(new ShopeeProductDetailProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public Job collectLZDJob(Step step1LZD) {
        return jobBuilderFactory.get("collectLazada")
                .incrementer(new RunIdIncrementer())
                .flow(step1LZD)
                .end()
                .build();
    }

    @Bean
    public Step step1LZD(ProductRESTAPIWriter writer) {
        return stepBuilderFactory.get("step1LZD")
                .<Map<PublisherProductMappingEntity, LazadaProductDetailDto>, List<PublisherProductDetailEntity>> chunk(10)
                .reader(new LazadaApiReader(publisherProductMappingRepository, new LazadaAPIFetcher()))
                .processor(new LazadaProductDetailProcessor())
                .writer(writer)
                .build();
    }
}
