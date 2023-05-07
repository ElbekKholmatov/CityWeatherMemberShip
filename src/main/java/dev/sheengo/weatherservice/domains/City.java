package dev.sheengo.weatherservice.domains;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City extends Auditable{
    private String name;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Location location;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Weather weather;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AuthUser> subscriptions;
    @Builder(builderMethodName = "childBuilder")
    public City(Long id, LocalDateTime createdAt, LocalDateTime updateAt, Long createdBy, Long updatedBy, boolean deleted, STATE state, String name, Location location, Weather weather, List<AuthUser> subscriptions) {
        super(id, createdAt, updateAt, createdBy, updatedBy, deleted, state);
        this.name = name;
        this.location = location;
        this.weather = weather;
        this.subscriptions = subscriptions;
    }
}
