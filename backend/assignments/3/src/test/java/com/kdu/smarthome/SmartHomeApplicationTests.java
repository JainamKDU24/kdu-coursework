package com.kdu.smarthome;

import com.kdu.smarthome.controller.AuthControllerTest;
import com.kdu.smarthome.controller.DeviceControllerTest;
import com.kdu.smarthome.controller.HouseControllerTest;
import com.kdu.smarthome.controller.InventoryControllerTest;
import com.kdu.smarthome.controller.RoomControllerTest;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class SmartHomeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Run authentication related tests.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(1)
    void runAuthTests() throws Exception {
        MvcResult mvcResult = AuthControllerTest.registerUser(mockMvc);

        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    /**
     * Run house related tests.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(2)
    void houseRegisterWithInvalidAuth() throws Exception {
        MvcResult output = HouseControllerTest.houseRegisterWithInvalidAuth(mockMvc);
        Assert.assertEquals(403, output.getResponse().getStatus());
    }

    @Test
    @Order(3)
    void houseRegisterWithValidRequestData() {
        MvcResult output = HouseControllerTest.houseRegisterWithValidRequestData(mockMvc);
        Assert.assertEquals(200, output.getResponse().getStatus());
    }

    /**
     * Run adding users to house related tests.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(4)
    void addUnregisteredUserToHouseByAdmin() throws Exception {
        MvcResult output = HouseControllerTest.addUnregisteredUserToHouseByAdmin(mockMvc);
        Assert.assertEquals(400, output.getResponse().getStatus());
    }

    @Test
    @Order(5)
    void addUserToHouseByNonAdmin() throws Exception {
        MvcResult output = HouseControllerTest.addUserToHouseByNonAdmin(mockMvc);
        Assert.assertEquals(400, output.getResponse().getStatus());
    }

    @Test
    @Order(6)
    void addUserToHouseByAdmin() throws Exception {
        MvcResult output = HouseControllerTest.addUserToHouseByAdmin(mockMvc);
        Assert.assertEquals(200, output.getResponse().getStatus());
    }

    /**
     * Run adding room to house related tests.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(7)
    void addRoomWithInvalidAuth() throws Exception {
        MvcResult output = RoomControllerTest.addRoomWithInvalidAuth(mockMvc);
        Assert.assertEquals(403, output.getResponse().getStatus());
    }

    @Test
    @Order(8)
    void addRoomByAdmin() throws Exception {
        MvcResult output = RoomControllerTest.addRoomByAdmin(mockMvc);
        Assert.assertEquals(200, output.getResponse().getStatus());
    }


    @Test
    @Order(9)
    void addRoomForInvalidHouse() throws Exception {
        MvcResult output = RoomControllerTest.addRoomForInvalidHouse(mockMvc);
        Assert.assertEquals(400, output.getResponse().getStatus());
    }

    /**
     * Tests to display all registered houses.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(10)
    void displayAllHouses() throws Exception {
        MvcResult output = HouseControllerTest.displayAllHouses(mockMvc);
        Assert.assertEquals(200, output.getResponse().getStatus());
    }

    @Test
    @Order(11)
    void displayAllHousesWithInvalidAuth() throws Exception {
        MvcResult output = HouseControllerTest.displayAllHousesWithInvalidAuth(mockMvc);
        Assert.assertEquals(403, output.getResponse().getStatus());
    }

    @Test
    @Order(11)
    void displayAllHousesByNonAdmin() throws Exception {
        MvcResult output = HouseControllerTest.displayAllHousesByNonAdmin(mockMvc);
        Assert.assertEquals(200, output.getResponse().getStatus());
    }

    /**
     * Tests to update house address.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(12)
    void updateAddressForHouse() throws Exception {
        MvcResult output = HouseControllerTest.updateAddressForHouse(mockMvc);
        Assert.assertEquals(200, output.getResponse().getStatus());
    }

    @Test
    @Order(13)
    void updateAddressForInvalidHouse() throws Exception {
        MvcResult output = HouseControllerTest.updateAddressForInvalidHouse(mockMvc);
        Assert.assertEquals(400, output.getResponse().getStatus());
    }

    /**
     * Run inventory related tests
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(14)
    void addDeviceToInventory() throws Exception {
        MvcResult output = InventoryControllerTest.addDeviceToInventory(mockMvc);
        Assert.assertEquals(200, output.getResponse().getStatus());
    }

    @Test
    @Order(15)
    void displayInventory() throws Exception {
        MvcResult output = InventoryControllerTest.displayInventory(mockMvc);
        Assert.assertEquals(200, output.getResponse().getStatus());
    }

    /**
     * Run device registration related tests
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(16)
    void registerUnavailableDevice() throws Exception {
        MvcResult output = DeviceControllerTest.registerUnavailableDevice(mockMvc);
        Assert.assertEquals(400, output.getResponse().getStatus());
    }

    @Test
    @Order(17)
    void deviceRegisterWithInvalidCredentials() throws Exception {
        MvcResult output = DeviceControllerTest.deviceRegisterWithInvalidCredentials(mockMvc);
        Assert.assertEquals(400, output.getResponse().getStatus());
    }

    @Test
    @Order(18)
    void deviceRegisterByNonAdmin() throws Exception {
        MvcResult output = DeviceControllerTest.deviceRegisterByNonAdmin(mockMvc);
        Assert.assertEquals(400, output.getResponse().getStatus());
    }

    @Test
    @Order(19)
    void deviceRegisterWithValidRequestData() throws Exception {
        MvcResult output = DeviceControllerTest.deviceRegisterWithValidRequestData(mockMvc);
        Assert.assertEquals(200, output.getResponse().getStatus());
    }

    /**
     * Tests to add device in a house
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(20)
    void addUnavailableDevice() throws Exception {
        MvcResult output = DeviceControllerTest.addUnavailableDevice(mockMvc);
        Assert.assertEquals(400, output.getResponse().getStatus());
    }

    @Test
    @Order(21)
    void addDeviceToInvalidHouse() throws Exception {
        MvcResult output = DeviceControllerTest.addDeviceToInvalidHouse(mockMvc);
        Assert.assertEquals(400, output.getResponse().getStatus());
    }

    @Test
    @Order(22)
    void addDeviceToInvalidRoom() throws Exception {
        MvcResult output = DeviceControllerTest.addDeviceToInvalidRoom(mockMvc);
        Assert.assertEquals(400, output.getResponse().getStatus());
    }

    @Test
    @Order(23)
    void addUnregisteredDevice() throws Exception {
        MvcResult output = DeviceControllerTest.addUnregisteredDevice(mockMvc);
        Assert.assertEquals(400, output.getResponse().getStatus());
    }

    @Test
    @Order(24)
    void addDeviceWithValidRequestData() throws Exception {
        MvcResult output = DeviceControllerTest.addDeviceWithValidRequestData(mockMvc);
        Assert.assertEquals(200, output.getResponse().getStatus());
    }

    /**
     * Tests to display all rooms and devices for a house
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(25)
    void listRoomsAndDevices() throws Exception {
        MvcResult output = HouseControllerTest.listRoomsAndDevices(mockMvc);
        Assert.assertEquals(200, output.getResponse().getStatus());
    }
}