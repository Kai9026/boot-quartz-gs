package com.github.kai9026.quartzgs.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JobResponse implements Serializable {
    private boolean success;
    private String jobId;
    private String jobGroup;
    private String message;

    public JobResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
