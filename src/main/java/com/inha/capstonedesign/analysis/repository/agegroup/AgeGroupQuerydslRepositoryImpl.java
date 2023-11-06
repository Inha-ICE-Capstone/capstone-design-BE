package com.inha.capstonedesign.analysis.repository.agegroup;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AgeGroupQuerydslRepositoryImpl implements AgeGroupQuerydslRepository{
    private final JPAQueryFactory queryFactory;
}
