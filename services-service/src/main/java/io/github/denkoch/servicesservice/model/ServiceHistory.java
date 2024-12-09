package io.github.denkoch.servicesservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "service_history")
public class ServiceHistory {

    @Id
    private String serviceHistoryId;

    private Long guestId;

//    @DBRef
//    private List<Service> service;
    @DBRef
    private Service service;

    private Date serviceDate;

}
