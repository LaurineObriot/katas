package fr.kata;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DiamondTest {

    private Diamond diamond = new Diamond();

    @Test
    public void createDiamond_shouldReturnDiamondSize1_whenUpToA() {
        // Given
        Character stopLetter = 'A';

        // When
        List<String> result = diamond.upTo(stopLetter);

        // Then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo("A");
    }

}
