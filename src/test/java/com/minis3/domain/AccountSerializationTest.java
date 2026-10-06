package com.minis3.domain;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AccountSerializationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void passwordIsNeverSerialized() throws Exception {
        Account account = accountWithPassword("test1234");

        String json = objectMapper.writeValueAsString(account);

        assertThat(json).doesNotContain("password").doesNotContain("test1234");
        assertThat(json).contains("\"username\":\"kenye\"");
    }

    @Test
    void passwordIsStillAcceptedOnDeserialization() throws Exception {
        String json = """
                {"id":1,"username":"kenye","password":"test1234"}
                """;

        Account account = objectMapper.readValue(json, Account.class);

        assertThat(account.getPassword()).isEqualTo("test1234");
    }

    @Test
    void equalsAndHashCodeTerminateOnBidirectionalGraph() {
        Account one = accountWithOwnFile(1L);
        Account two = accountWithOwnFile(1L);

        assertThat(one).isEqualTo(two);
        assertThat(one.hashCode()).isEqualTo(two.hashCode());
    }

    private static Account accountWithPassword(String password) {
        Account account = new Account();
        account.setId(1L);
        account.setUsername("kenye");
        account.setPassword(password);
        account.setFiles(List.of());
        return account;
    }

    private static Account accountWithOwnFile(Long id) {
        FileMetadata file = new FileMetadata();
        file.setId(id);
        file.setFileName("a.txt");

        Account account = new Account();
        account.setId(id);
        account.setUsername("kenye");
        account.setPassword("test1234");
        file.setAccount(account);
        account.setFiles(List.of(file));

        return account;
    }
}
