package com.example.mildangbespringstudy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CleanUp {

    private final JdbcTemplate jdbcTemplate;

    private final EntityManager entityManager;

    @Transactional
    public void all() {
        List<String> tables = entityManager.getMetamodel().getEntities().stream()
                .map(this::getTableName)
                .collect(Collectors.toList());

        tables.forEach(table -> jdbcTemplate.execute("TRUNCATE TABLE " + table));
    }

    private String getTableName(EntityType<?> entityType) {
        Table tableAnnotation = entityType.getJavaType().getAnnotation(Table.class);
        return (tableAnnotation != null && !tableAnnotation.name().isEmpty())
                ? tableAnnotation.name()
                : entityType.getName();
    }
}
