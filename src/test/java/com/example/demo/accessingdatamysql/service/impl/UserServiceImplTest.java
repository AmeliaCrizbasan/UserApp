package com.example.demo.accessingdatamysql.service.impl;

import com.example.demo.accessingdatamysql.dto.*;
import com.example.demo.accessingdatamysql.model.Country;
import com.example.demo.accessingdatamysql.model.User;
import com.example.demo.accessingdatamysql.model.UserAddress;
import com.example.demo.accessingdatamysql.model.UserProfile;
import com.example.demo.accessingdatamysql.repository.AddressRepository;
import com.example.demo.accessingdatamysql.repository.UserProfileRepository;
import com.example.demo.accessingdatamysql.repository.UserRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.validation.FieldError;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final String CITY_1 = "Brasov";

    private static final String STREET_1 = "Aurel Vlaicu";
    private static final String STREET_2 = "Strada Lacramioarelor";

    private static final String COUNTRY_1 = "Romania";

    private static final String USER_NAME_1 = "Amelia";
    private static final String USER_NAME_2 = "Cristi";
    private static final String USER_NAME_3 = "Maria";
    private static final String USER_NAME_4 = "Alina";

    private static final String EMAIL_1 = "crizbasan@yahoo.com";
    private static final String EMAIL_2 = "alex@gmail.com";
    private static final String EMAIL_3 = "maria@gmail.com";
    private static final String EMAIL_4 = "alina@yahoo.com";

    private static final Boolean ISPROFILE_1 = false;
    private static final Boolean ISPROFILE_2 = true;


    @Rule
    //It refer to the rules or methods that returns a rule. Rules - as a component that is used to obstruct the test method calls and allows us to perform something before and after the test method is invoked
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock  // A fake object that simulates the behavior of real objects in a controlled manner.
    private UserRepository userRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private Validator validator;
    @Captor
    // to create an ArgumentCaptor instance. Allows us to capture an argument passed to a method in order to inspect it.
    private ArgumentCaptor<User> captor;
    @InjectMocks
    private UserServiceImpl victim;


    @Test
    public void saveUserTest() throws ConstraintViolationException {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName(USER_NAME_1);
        createUserRequest.setEmail(EMAIL_1);
        UserAddressDTO userAddress = new UserAddressDTO();
        userAddress.setStreetName(STREET_2);
        userAddress.setCity(CITY_1);
        CountryDTO country = new CountryDTO();
        userAddress.setCountry(country);
        createUserRequest.setUserAddress(Collections.singleton(userAddress));

        when(userRepository.save(any())).thenReturn(new User());

        UserResponse act = victim.saveUser(createUserRequest);

        Set<ConstraintViolation<CreateUserRequest>> constraintViolations = validator.validate(createUserRequest);

        assertEquals(constraintViolations.size(), 0);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(
                    new HashSet<ConstraintViolation<?>>(constraintViolations));

        }

        verify(userRepository, times(1)).save(captor.capture());
        assertEquals(captor.getValue().getName(), USER_NAME_1);
        assertEquals(captor.getValue().getEmail(), EMAIL_1);
        Optional<UserAddress> actualAddress = captor.getValue().getUserAddress().stream().findFirst();
        assertTrue(actualAddress.isPresent());
        assertEquals(actualAddress.get().getStreetName(), STREET_2);
        assertEquals(actualAddress.get().getCity(), CITY_1);
        assertNotNull(actualAddress.get().getCountry());

    }

    @Test
    public void getAllUsersProfileNullTest() {
        List<User> userList = new ArrayList<>();
        userList.add(createUser(USER_NAME_2, EMAIL_2, ISPROFILE_1));
        userList.add(createUser(USER_NAME_3, EMAIL_3, ISPROFILE_2));

        when(userRepository.findAll()).thenReturn(userList);

        List<UserResponse> actualUserResponse = victim.getAllUsers();

        assertEquals(actualUserResponse.size(), 2);
        for (UserResponse user : actualUserResponse) {
            assertEquals(user.getUserAddress().size(), 1);
            assertEquals(user.getUserAddress().get(0).getId().intValue(), 1);
            assertEquals(user.getUserAddress().get(0).getStreetName(), STREET_1);
            assertEquals(user.getUserAddress().get(0).getCity(), CITY_1);
            assertEquals(user.getUserAddress().get(0).getCountry().getCountryName(), COUNTRY_1);
            if (user.getName().equals(USER_NAME_2)) {
                assertEquals(user.getEmail(), EMAIL_2);
                assertNull(user.getUserProfile());
            } else {
                assertEquals(user.getEmail(), EMAIL_3);
                assertNotNull(user.getUserProfile());
            }
        }
    }

    @Test
    public void getUserByIdTestUserPresent() {
        Optional<User> userOptional = Optional.of(createUser(USER_NAME_4, EMAIL_4, ISPROFILE_1));

        when(userRepository.findById(any())).thenReturn(userOptional);

        UserResponse actualResponse = victim.getUserById(1);

        assertEquals(actualResponse.getName(), USER_NAME_4);
        assertEquals(actualResponse.getEmail(), EMAIL_4);
        assertEquals(actualResponse.getUserProfile(), null);
    }

    @Test
    public void getUserByIdTestUserNotPresent() {
        Optional<User> userOptional = Optional.empty();

        when(userRepository.findById(any())).thenReturn(userOptional);

        UserResponse actualResponse = victim.getUserById(1);

        assertNull(actualResponse);
    }

    @Test
    public void getAllAddresses() {
        List<UserAddress> userAddressesList = new ArrayList<>();

        userAddressesList.add(createUserAddress(1, STREET_1, CITY_1, COUNTRY_1));
        //userAddressesList.add(createUserAddress(2,"Calea Bucuresti ", COUNTRY_1,"Romania"));

        when(addressRepository.findAll()).thenReturn(userAddressesList);
        List<AddressResponse> actualAddressResponse = victim.getAllAddresses();

        assertEquals(actualAddressResponse.size(), 1);
        for (AddressResponse address : actualAddressResponse) {
            assertEquals(address.getStreetName(), STREET_1);
            assertEquals(address.getCity(), CITY_1);
            assertEquals(address.getCountry().getCountryName(), COUNTRY_1);
        }
    }


    private User createUser(String name, String email, boolean isProfile) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setUserAddress(new HashSet<>(List.of(createUserAddress(1, STREET_1, CITY_1, COUNTRY_1))));
        if (isProfile) {
            user.setUserProfile(new UserProfile());
        }
        return user;
    }

    private UserAddress createUserAddress(Integer id, String streetName, String city, String countryName) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(id);
        userAddress.setStreetName(streetName);
        userAddress.setCity(city);
        Country country = new Country();
        country.setId(20);
        country.setCountryName(countryName);
        userAddress.setCountry(country);
        return userAddress;
    }

}