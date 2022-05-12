package com.example.interview.transaction;

import com.example.interview.customer.Customer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;

public class TransactionSerializer extends StdSerializer<Transaction> {

    public TransactionSerializer() {
        this(null);
    }

    public TransactionSerializer(Class<Transaction> t) {
        super(t);
    }

    @Override
    public void serialize(
            Transaction value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("id", value.getId().toString());
        jgen.writeNumberField("amount", value.getAmount());
        jgen.writeArrayFieldStart("customers");

        Set<Customer> temp = value.getFrom().getCustomers();
        temp.addAll(value.getTo().getCustomers());

        for (Customer c : temp) {
            jgen.writeStartObject();
            jgen.writeStringField("id", c.getId().toString());
            jgen.writeStringField("firstName", c.getFirstName());
            jgen.writeStringField("lastName", c.getLastName());
            jgen.writeEndObject();
        }

        jgen.writeEndArray();
        jgen.writeEndObject();
    }
}