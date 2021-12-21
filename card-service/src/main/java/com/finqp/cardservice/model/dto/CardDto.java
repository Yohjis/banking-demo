package com.finqp.cardservice.model.dto;
import com.finqp.cardservice.model.Banking;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class CardDto {
    String banking;
    String number;
}