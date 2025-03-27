package edu.jwt.biponline.response;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class BaseResponse {
    protected boolean success;
    protected String message;

    public BaseResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


}
