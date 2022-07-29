package com.users.application.components.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "city",
        "street"
})
public class AddressRequest {
    public String city;
    public String street;
}
