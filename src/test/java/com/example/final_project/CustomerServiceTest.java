package com.example.final_project;


import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    List<User> users = new ArrayList<>();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        users.add(new User("tchico", "tchicopassword"));
        when(userRepository.save(any(User.class))).thenReturn(users.get(0));
        User user = new User("tchico", "tchicopassword");
        User savedUser = userService.registerUser(user);
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getPassword(), savedUser.getPassword());
    }

    @Test
    public void testNullAndEmptyParams() {
        //empty username
        Exception emptyUsernameException = assertThrows(IllegalArgumentException.class, () -> {
            new User("", "tchicopassword");
        });
        assertEquals("Username cannot be null or empty", emptyUsernameException.getMessage());
        //null username
        Exception nullUsernameException = assertThrows(IllegalArgumentException.class, () -> {
            new User(null, "tchicopassword");
        });
        assertEquals("Username cannot be null or empty", nullUsernameException.getMessage());
        //empty password
        Exception emptyPasswordException = assertThrows(IllegalArgumentException.class, () -> {
            new User("tchico", "");
        });
        assertEquals("Password cannot be null or empty", emptyPasswordException.getMessage());
        //null password
        Exception nullPasswordException = assertThrows(IllegalArgumentException.class, () -> {
            new User("tchico", null);
        });
        assertEquals("Password cannot be null or empty", nullPasswordException.getMessage());
    }

    @Test
    public void testLoginSuccess() {
        users.add(new User("tchico", "tchicopassword"));
        when(userRepository.findByUsername(any(String.class))).thenReturn(users.get(0));
        User user = userService.login("tchico", "tchicopassword");
        assertEquals(users.get(0), user);
    }

    @Test
    public void testLoginFailure() {
        when(userRepository.findByUsername(any(String.class))).thenReturn(null);
        User user = userService.login("tchico", "wrongPassword");
        assertEquals(null, user);
    }


    @Test
    public void testDeleteUser() {
        users.add(new User("tchico", "tchicopassword"));
        when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(users.get(0)));
        userService.deleteUser(users.get(0).getUserId());
        verify(userRepository).deleteById(users.get(0).getUserId());
        when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> userService.deleteUser(users.get(0).getUserId()));
    }


}
