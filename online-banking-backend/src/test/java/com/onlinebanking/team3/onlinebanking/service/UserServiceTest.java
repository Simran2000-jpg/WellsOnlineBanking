package com.onlinebanking.team3.onlinebanking.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.onlinebanking.team3.onlinebanking.exception.UserNotFoundException;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#registerUser(User)}
     */
    @Test
    void testRegisterUser() {
        User user = new User();
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        assertSame(user, userService.registerUser(new User()));
        verify(userRepository).save(Mockito.<User>any());
    }

    /**
     * Method under test: {@link UserService#registerUser(User)}
     */
    @Test
    void testRegisterUser2() {
        when(userRepository.save(Mockito.<User>any())).thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class, () -> userService.registerUser(new User()));
        verify(userRepository).save(Mockito.<User>any());
    }

    /**
     * Method under test: {@link UserService#listAll()}
     */
    @Test
    void testListAll() {
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);
        List<User> actualListAllResult = userService.listAll();
        assertSame(userList, actualListAllResult);
        assertTrue(actualListAllResult.isEmpty());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserService#listAll()}
     */
    @Test
    void testListAll2() {
        when(userRepository.findAll()).thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class, () -> userService.listAll());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserService#findUserByPhoneNumber(String)}
     */
    @Test
    void testFindUserByPhoneNumber() {
        Optional<User> ofResult = Optional.of(new User());
        when(userRepository.findByPhoneNumber(Mockito.<String>any())).thenReturn(ofResult);
        Optional<User> actualFindUserByPhoneNumberResult = userService.findUserByPhoneNumber("6625550144");
        assertSame(ofResult, actualFindUserByPhoneNumberResult);
        assertTrue(actualFindUserByPhoneNumberResult.isPresent());
        verify(userRepository).findByPhoneNumber(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#findUserByPhoneNumber(String)}
     */
    @Test
    void testFindUserByPhoneNumber2() {
        when(userRepository.findByPhoneNumber(Mockito.<String>any()))
                .thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class, () -> userService.findUserByPhoneNumber("6625550144"));
        verify(userRepository).findByPhoneNumber(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#getUserById(Long)}
     */
    @Test
    void testGetUserById() {
        User user = new User();
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(user));
        assertSame(user, userService.getUserById(1L));
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#getUserById(Long)}
     */
    @Test
    void testGetUserById2() {
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.getUserById(1L));
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#getUserById(Long)}
     */
    @Test
    void testGetUserById3() {
        when(userRepository.findById(Mockito.<Long>any())).thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#updateUser(Long, User)}
     */
    @Test
    void testUpdateUser() {
        User user = new User();
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        assertSame(user, userService.updateUser(1L, new User()));
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#updateUser(Long, User)}
     */
    @Test
    void testUpdateUser2() {
        when(userRepository.save(Mockito.<User>any())).thenThrow(new UserNotFoundException("An error occurred"));
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, new User()));
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#updateUser(Long, User)}
     */
    @Test
    void testUpdateUser3() {
        User user = mock(User.class);
        doThrow(new EntityNotFoundException("An error occurred")).when(user).setFirstName(Mockito.<String>any());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(EntityNotFoundException.class, () -> userService.updateUser(1L, new User()));
        verify(userRepository).findById(Mockito.<Long>any());
        verify(user).setFirstName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#updateUser(Long, User)}
     */
    @Test
    void testUpdateUser4() {
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        new EntityNotFoundException("An error occurred");
        new EntityNotFoundException("An error occurred");
        new EntityNotFoundException("An error occurred");
        new EntityNotFoundException("An error occurred");
        new EntityNotFoundException("An error occurred");
        new EntityNotFoundException("An error occurred");
        new EntityNotFoundException("An error occurred");
        new EntityNotFoundException("An error occurred");
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, new User()));
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#updateUser(Long, User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUser5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.onlinebanking.team3.onlinebanking.model.User.getFirstName()" because "updatedUser" is null
        //       at com.onlinebanking.team3.onlinebanking.service.UserService.updateUser(UserService.java:46)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = mock(User.class);
        doThrow(new EntityNotFoundException("An error occurred")).when(user).setEmailId(Mockito.<String>any());
        doThrow(new EntityNotFoundException("An error occurred")).when(user).setFatherName(Mockito.<String>any());
        doThrow(new EntityNotFoundException("An error occurred")).when(user).setFirstName(Mockito.<String>any());
        doThrow(new EntityNotFoundException("An error occurred")).when(user).setGender(Mockito.<String>any());
        doThrow(new EntityNotFoundException("An error occurred")).when(user).setLastName(Mockito.<String>any());
        doThrow(new EntityNotFoundException("An error occurred")).when(user).setMiddleName(Mockito.<String>any());
        doThrow(new EntityNotFoundException("An error occurred")).when(user).setOccupation(Mockito.<String>any());
        doThrow(new EntityNotFoundException("An error occurred")).when(user).setSourceOfIncome(Mockito.<String>any());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.save(Mockito.<User>any())).thenReturn(new User());
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        userService.updateUser(1L, null);
    }

    /**
     * Method under test: {@link UserService#kycVerifyUser(Long)}
     */
    @Test
    void testKycVerifyUser() {
        User user = new User();
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        assertSame(user, userService.kycVerifyUser(1L));
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#kycVerifyUser(Long)}
     */
    @Test
    void testKycVerifyUser2() {
        when(userRepository.save(Mockito.<User>any())).thenThrow(new UserNotFoundException("An error occurred"));
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        assertThrows(UserNotFoundException.class, () -> userService.kycVerifyUser(1L));
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#kycVerifyUser(Long)}
     */
    @Test
    void testKycVerifyUser3() {
        User user = new User();
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        Address residentialAddress = new Address("42 Main St", "Oxford", "MD");

        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User(1L, "Jane", "Middle Name",
                "Doe", "6625550144", "42", "42", "42", "Dob", "Occupation", "Source Of Income", "Gross Annual Income",
                "Gender", "iloveyou", true, "Father Name", residentialAddress, new Address("42 Main St", "Oxford", "MD"))));
        assertSame(user, userService.kycVerifyUser(1L));
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#kycVerifyUser(Long)}
     */
    @Test
    void testKycVerifyUser4() {
        User user = mock(User.class);
        when(user.getKyc()).thenThrow(new EntityNotFoundException("An error occurred"));
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(EntityNotFoundException.class, () -> userService.kycVerifyUser(1L));
        verify(userRepository).findById(Mockito.<Long>any());
        verify(user).getKyc();
    }

    /**
     * Method under test: {@link UserService#kycVerifyUser(Long)}
     */
    @Test
    void testKycVerifyUser5() {
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        new EntityNotFoundException("An error occurred");
        new EntityNotFoundException("An error occurred");
        assertThrows(UserNotFoundException.class, () -> userService.kycVerifyUser(1L));
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#deleteUser(Long)}
     */
    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(Mockito.<Long>any());
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        userService.deleteUser(1L);
        verify(userRepository).findById(Mockito.<Long>any());
        verify(userRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#deleteUser(Long)}
     */
    @Test
    void testDeleteUser2() {
        doThrow(new UserNotFoundException("An error occurred")).when(userRepository).deleteById(Mockito.<Long>any());
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
        verify(userRepository).findById(Mockito.<Long>any());
        verify(userRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#deleteUser(Long)}
     */
    @Test
    void testDeleteUser3() {
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
        verify(userRepository).findById(Mockito.<Long>any());
    }
}

