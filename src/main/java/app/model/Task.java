package app.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {

    private static final AtomicLong idGenerator = new AtomicLong(1);

    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String body;

    private byte importanceLevel;

    private String stage;

    private LocalDate createdAt = LocalDate.now();

    private LocalDate dueDate;

    public Task() {
        this.id = idGenerator.getAndIncrement();
    }

}
