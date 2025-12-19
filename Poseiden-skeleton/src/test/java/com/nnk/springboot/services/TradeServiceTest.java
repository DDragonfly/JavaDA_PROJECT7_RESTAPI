package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    @Test
    void findAll_shouldReturnList() {
        when(tradeRepository.findAll()).thenReturn(List.of(new Trade()));

        List<Trade> result = tradeService.findAll();

        assertEquals(1, result.size());
        verify(tradeRepository).findAll();
    }

    @Test
    void save_shouldCallRepository() {
        Trade trade = new Trade();
        when(tradeRepository.save(trade)).thenReturn(trade);

        Trade saved = tradeService.save(trade);
        assertSame(trade, saved);
        verify(tradeRepository).save(trade);
    }

    @Test
    void findById_shouldReturnEntity() {
        Trade trade = new Trade();
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        Trade result = tradeService.findById(1);
        assertSame(trade, result);
        verify(tradeRepository).findById(1);
    }

    @Test
    void findById_shouldThrowException_whenRatingNotFound() {
        when(tradeRepository.findById(99)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> tradeService.findById(99));

        assertTrue(ex.getMessage().contains("Invalid Trade Id"));
        verify(tradeRepository).findById(99);
    }

    @Test
    void deleteById_shouldCallRepository() {
        tradeService.deleteById(5);
        verify(tradeRepository).deleteById(5);
    }

}
