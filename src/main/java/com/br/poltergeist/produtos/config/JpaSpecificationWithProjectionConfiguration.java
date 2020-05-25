package com.br.poltergeist.produtos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import th.co.geniustree.springdata.jpa.repository.support.JpaSpecificationExecutorWithProjectionImpl;

@Configuration
@EnableJpaRepositories(basePackages = "com.br.poltergeist.produtos.repository", repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class)
public class JpaSpecificationWithProjectionConfiguration {
}
