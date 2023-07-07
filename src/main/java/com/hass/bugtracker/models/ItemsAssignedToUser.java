//package com.hass.bugtracker.models;
//
//import jakarta.persistence.*;
//
//@Entity
//public class ItemsAssignedToUser {
//    @Id
//    @GeneratedValue (
//            strategy = GenerationType.SEQUENCE,
//            generator = "items_assigned_to_user_sequence"
//    )
//    @SequenceGenerator(
//            name = "items_assigned_to_user_sequence",
//            sequenceName = "items_assigned_to_user_sequence"
//    )
//    private Integer id;
//
////    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
////    @JoinColumn(name = "user_id")
////    private User user;
////    private Item item;
//
//}
