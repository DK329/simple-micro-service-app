package lk.ijse.dep10.backendspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Pattern(regexp = "\\d{10,13}", message = "Invalid isbn")
    private String isbn;
    @NotBlank(message = "Contact title can't be empty or null")
    private String title;
}
