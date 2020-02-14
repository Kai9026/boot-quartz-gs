package com.github.kai9026.quartzgs.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class JobRequest implements Serializable {
    @NotNull
    private LocalDateTime timeTrigger;

    @NotNull
    private ZoneId timeZone;

}
