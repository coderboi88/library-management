package com.aditya.librarymanagement.service;

import com.aditya.librarymanagement.model.Book;
import com.aditya.librarymanagement.model.request.LoginRequest;
import com.aditya.librarymanagement.model.request.SignupRequest;
import com.aditya.librarymanagement.model.response.JwtResponse;
import com.aditya.librarymanagement.model.response.MessageResponse;
import com.aditya.librarymanagement.repository.BookRepository;
//import com.aditya.librarymanagement.repository.RoleRepository;
//import com.aditya.librarymanagement.repository.UserRepository;
//import com.aditya.librarymanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
//    private UserRepository userRepository;
//    private RoleRepository roleRepository;
//    //private PasswordEncoder encoder;
    //private JwtUtil jwtUtil;
    //private AuthenticationManager authenticationManager;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        //this.encoder = encoder;
        //this.jwtUtil = jwtUtil;
        //this.authenticationManager = authenticationManager;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(String bookId) {
        bookRepository.deleteById(Integer.parseInt(bookId));
    }

    public Book getBook(String bookId) {
        return bookRepository.findByBookId(Integer.parseInt(bookId));
    }

    public MessageResponse registerUser(SignupRequest signUpRequest) {
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
//        // Create new user's account
//        User user = new User(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()));
//        Set<String> strRoles = signUpRequest.getRoles();
//        Set<Role> roles = new HashSet<>();
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//                        break;
//                    case "user":
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//        user.setRoles(roles);
//        userRepository.save(user);
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        return restTemplate.postForObject("http://auth-service/api/auth/signup",signUpRequest, MessageResponse.class);
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
//        String jwt = jwtUtil.generateToken(userDetails);
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername(),
//                userDetails.getEmail(),
//                roles));

        return restTemplate.postForObject("http://auth-service/api/auth/signin",loginRequest,JwtResponse.class);
    }
}
