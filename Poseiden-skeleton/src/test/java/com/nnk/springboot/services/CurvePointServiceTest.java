package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
    private CurvePointService curvePointService;

    @Test
    void findAll_shouldReturnListOfCurvePoints() {
        CurvePoint c1 = new CurvePoint(1, 10d, 100d);
        CurvePoint c2 = new CurvePoint(2, 20d, 200d);
        when(curvePointRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<CurvePoint> result = curvePointService.findAll();

        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(c1, c2);
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    void save_shouldCallRepositorySaveAndReturnEntity() {
        CurvePoint curvePoint = new CurvePoint(1, 10d, 100d);
        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);

        CurvePoint result = curvePointService.save(curvePoint);

        assertThat(result).isEqualTo(curvePoint);
        verify(curvePointRepository, times(1)).save(curvePoint);
    }

    @Test
    void findById_existingIt_shouldReturnCurvePoint() {
        CurvePoint curvePoint = new CurvePoint(1, 10d, 100d);
        curvePoint.setId(1);
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));

        CurvePoint result = curvePointService.findById(1);

        assertThat(result).isEqualTo(curvePoint);
        verify(curvePointRepository, times(1)).findById(1);
    }

    @Test
    void findById_unknownId_shouldThrowException() {
        when(curvePointRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> curvePointService.findById(999));

        verify(curvePointRepository, times(1)).findById(999);
    }

    @Test
    void deleteById_shouldCallRepositoryDeleteById() {
        curvePointService.deleteById(1);
        verify(curvePointRepository, times(1)).deleteById(1);
    }
}
