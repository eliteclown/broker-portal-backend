package com.projects.brokerPortal.entities;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "quotelists")

//QuoteList Entity for handling quoteList related operations
public class QuoteListEntity {
    @Id //sets quoteListId as primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //auto-increment
    private Long quoteListId;

    @OneToOne //one to one mapping between quotes and quoteList table
    @JoinColumn(name = "quote_id",referencedColumnName = "quoteId")
    private QuoteEntity quotes;

    @ManyToOne // many-to-one mapping between quotes and brokers table
    @JoinColumn(name = "broker_id",referencedColumnName = "brokerId")
    private BrokerEntity broker;
    private Long customerId;
    @Column(nullable = false)
    private String quoteStringId;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String qualification;
    @Column(nullable = false)
    private String specialiaztion;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private BigDecimal premium;
    @CreationTimestamp
    private LocalDateTime createdAt;//creates present date
    private String status;
}
