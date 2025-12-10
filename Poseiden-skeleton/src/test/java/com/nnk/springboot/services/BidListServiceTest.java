package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
public class BidListServiceTest {

    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListService bidListService;

    @Test
    void findAll_shouldReturnListOfBidLists() {
        // given
        BidList bid1 = new BidList("Account1", "Type1", 10d);
        BidList bid2 = new BidList("Account2", "Type2", 20d);
        when(bidListRepository.findAll()).thenReturn(Arrays.asList(bid1, bid2));

        // when
        List<BidList> result = bidListService.findAll();

        // then
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(bid1, bid2);
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    void save_shouldCallRepositorySaveAndReturnEntity() {
        // given
        BidList bid = new BidList("Account Test", "Type Test", 10d);
        when(bidListRepository.save(bid)).thenReturn(bid);

        // when
        BidList result = bidListService.save(bid);

        // then
        assertThat(result).isEqualTo(bid);
        verify(bidListRepository, times(1)).save(bid);
    }

    @Test
    void findById_existingId_shouldReturnBidList() {
        // given
        BidList bid = new BidList("Account Test", "Type Test", 10d);
        bid.setBidListId(1);
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid));

        // when
        BidList result = bidListService.findById(1);

        // then
        assertThat(result).isEqualTo(bid);
        verify(bidListRepository, times(1)).findById(1);
    }

    @Test
    void findById_unknownId_shouldThrowException() {
        // given
        when(bidListRepository.findById(999)).thenReturn(Optional.empty());

        // when then
        assertThrows(IllegalArgumentException.class, () -> bidListService.findById(999));
        verify(bidListRepository, times(1)).findById(999);
    }

    @Test
    void deleteById_shouldCallRepositoryDeleteById() {
        // when
        bidListService.deleteById(1);

        // then
        verify(bidListRepository, times(1)).deleteById(1);
    }
}
