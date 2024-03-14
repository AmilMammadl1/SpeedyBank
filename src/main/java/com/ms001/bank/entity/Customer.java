package com.ms001.bank.entity;

import com.ms001.bank.constant.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    @Column(unique = true)
    private String finCode;
    private String lastName;
    private String fatherName;
    private String passwrd;
    @Column(unique = true)
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));//returns roles of Customer clas
    }

    @Override
    public String getPassword() {
        return passwrd;
    }

    @Override
    public String getUsername() {
        return finCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Account account;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();

    public Customer() {
        // Create a new Account with default values and set bidirectional relationship
        this.account = new Account();
        this.account.setCreatedAt(new Date());
        this.account.setCustomer(this);
        this.account.setBalanceTotal(0.0);
        this.account.setActive(true);
    }
}




