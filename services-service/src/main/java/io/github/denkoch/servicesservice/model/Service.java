package io.github.denkoch.servicesservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "services")
public class Service {

    @Id
    private String serviceId;

    @Indexed(unique = true)
    private String serviceName;

    private Long serviceCost;

//    @DBRef
//    private List<ServiceHistory> serviceHistory;
    @DBRef
    private ServiceHistory serviceHistory;
}

