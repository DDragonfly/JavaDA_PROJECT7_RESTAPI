package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
public class RuleNameServiceTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @InjectMocks
    private RuleNameService ruleNameService;

    @Test
    void findAll_shouldReturnListOfRatings() {
        when(ruleNameRepository.findAll()).thenReturn(List.of(new RuleName()));

        List<RuleName> result = ruleNameService.findAll();

        assertEquals(1, result.size());
        verify(ruleNameRepository).findAll();
    }

    @Test
    void save_shouldCallRepository() {
        RuleName ruleName = new RuleName();
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);

        RuleName savedRule = ruleNameService.save(ruleName);
        assertSame(ruleName, savedRule);
        verify(ruleNameRepository).save(ruleName);
    }

    @Test
    void findById_shouldReturnEntity() {
        RuleName ruleName = new RuleName();
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));

        RuleName result = ruleNameService.findById(1);
        assertSame(ruleName, result);
        verify(ruleNameRepository).findById(1);
    }

    @Test
    void findById_shouldThrowException_whenRatingNotFound() {
        when(ruleNameRepository.findById(99)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ruleNameService.findById(99));

        assertTrue(ex.getMessage().contains("Invalid RuleName Id"));
        verify(ruleNameRepository).findById(99);
    }

    @Test
    void deleteById_shouldCallRepository() {
        ruleNameService.deleteById(5);
        verify(ruleNameRepository).deleteById(5);
    }
}
