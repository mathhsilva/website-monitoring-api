package com.matheus.websitemonitoring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebsiteRequest {

    @NotBlank(message = "O nome do site é obrigatório")
    @Size(max = 120, message = "O nome do site deve ter no máximo 120 caracteres")
    private String name;

    @NotBlank(message = "A URL é obrigatória")
    @Size(max = 300, message = "A URL deve ter no máximo 300 caracteres")
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "A URL deve começar com http:// ou https://"
    )
    private String url;
}