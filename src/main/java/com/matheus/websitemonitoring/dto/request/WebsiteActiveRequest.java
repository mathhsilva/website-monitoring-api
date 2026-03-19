package com.matheus.websitemonitoring.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebsiteActiveRequest {

    @NotNull(message = "O campo active é obrigatório")
    private Boolean active;
}