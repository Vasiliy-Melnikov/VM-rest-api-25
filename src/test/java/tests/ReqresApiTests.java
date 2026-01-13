package tests;

import models.*;
import org.junit.jupiter.api.Test;
import steps.ReqresSteps;

import static org.junit.jupiter.api.Assertions.*;

public class ReqresApiTests {

    private final ReqresSteps steps = new ReqresSteps();

    @Test
    void checkListUsersTest() {
        ListUsersResponse response = steps.getUsers(2);

        assertEquals(12, response.getTotal());
        assertNotNull(response.getData());
        assertEquals(6, response.getData().size());
    }

    @Test
    void createUserTest() {
        CreateUserBody body = new CreateUserBody("morpheus", "leader");

        CreateUserResponse response = steps.createUser(body);

        assertEquals("morpheus", response.getName());
        assertEquals("leader", response.getJob());
        assertNotNull(response.getId());
        assertNotNull(response.getCreatedAt());
    }

    @Test
    void updateUserTest() {
        CreateUserBody body = new CreateUserBody("morpheus", "zion resident");

        UpdateUserResponse response = steps.updateUser(2, body);

        assertEquals("zion resident", response.getJob());
        assertNotNull(response.getUpdatedAt());
    }

    @Test
    void deleteUserTest() {
        steps.deleteUser(2);
    }

    @Test
    void unsuccessfulRegisterTest() {
        RegisterBody body = new RegisterBody("sydney@fife", null);

        RegisterErrorResponse response = steps.unsuccessfulRegister(body);

        assertEquals("Missing password", response.getError());
    }
}