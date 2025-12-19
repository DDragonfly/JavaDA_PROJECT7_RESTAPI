package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {
    @Mock
    RatingRepository ratingRepository;

    @InjectMocks
    RatingService ratingService;

    @Test
    void findAll_shouldReturnListOfRatings() {
        when(ratingRepository.findAll()).thenReturn(List.of(new Rating()));

        List<Rating> ratings = ratingService.findAll();

        assertEquals(1, ratings.size());
        verify(ratingRepository).findAll();
    }

    @Test
    void save_shouldCallRepository() {
        Rating rating = new Rating("moody", "sand", "fitch", 1);
        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating savedRating = ratingService.save(rating);
        assertSame(rating, savedRating);
        verify(ratingRepository).save(rating);
    }

    @Test
    void findById_shouldReturnEntity() {
        Rating rating = new Rating("moody", "sand", "fitch", 1);
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        Rating result = ratingService.findById(1);
        assertSame(rating, result);
        verify(ratingRepository).findById(1);
    }

    @Test
    void findById_shouldThrowException_whenRatingNotFound() {
        when(ratingRepository.findById(99)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ratingService.findById(99));

        assertTrue(ex.getMessage().contains("Invalid rating Id"));
        verify(ratingRepository).findById(99);
    }

    @Test
    void deleteById_shouldCallRepository() {
        ratingService.deleteById(5);
        verify(ratingRepository).deleteById(5);
    }

}
