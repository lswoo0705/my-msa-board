package mymsa.board.article.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PageLimitCalculatorTest {
    @Test
    void calculatePageLimitTest() {
        calculatePageLimitTest(1L, 30L, 10L, 301L);
        calculatePageLimitTest(5L, 30L, 10L, 301L);
        calculatePageLimitTest(10L, 30L, 10L, 301L);
        calculatePageLimitTest(15L, 30L, 10L, 601L);
        calculatePageLimitTest(20L, 30L, 10L, 601L);
    }

    void calculatePageLimitTest(Long page, Long pageSize, Long movablePageCount, Long expected) {
        Long result = PageLimitCalculator.calculatePageLimit(page, pageSize, movablePageCount);
        assertThat(result).isEqualTo(expected);
    }
}