package com.paypal.bfs.test.employeeserv.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "employee")
public class EmployeeEntity {

    @Id
    @Column(name="id", unique = true)
    private Integer id;

    @Column(name = "first_name",  nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private Date dob;

    @Column(name = "address", columnDefinition = "mediumtext")
    private String address;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private Date createdAt;

    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;
}
