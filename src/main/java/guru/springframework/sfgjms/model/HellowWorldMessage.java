package guru.springframework.sfgjms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HellowWorldMessage implements Serializable {
    private final long serialVersionID=234253453636l;
    private UUID uid;
    private String mesasge;

}
