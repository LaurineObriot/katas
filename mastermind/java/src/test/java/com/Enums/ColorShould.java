package com.enums;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorShould {

    @Test
    public void contain_red_yellow_blue_orange_green_and_black() {
        //Given

        //When
        Color[] values = Color.values();

        //Then
        assertThat(values).hasSize(6)
                .contains(Color.ROUGE, Color.JAUNE, Color.BLEU, Color.ORANGE, Color.VERT, Color.NOIR);
        assertThat(Color.ROUGE.getValue()).isEqualTo("R");
    }

    @Test
    public void have_colors_mapped_with_correct_value() {
        assertThat(Color.ROUGE.getValue()).isEqualTo("R");
        assertThat(Color.JAUNE.getValue()).isEqualTo("J");
        assertThat(Color.BLEU.getValue()).isEqualTo("B");
        assertThat(Color.ORANGE.getValue()).isEqualTo("O");
        assertThat(Color.VERT.getValue()).isEqualTo("V");
        assertThat(Color.NOIR.getValue()).isEqualTo("N");
    }
}
